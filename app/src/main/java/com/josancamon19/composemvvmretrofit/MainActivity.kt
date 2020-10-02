package com.josancamon19.composemvvmretrofit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.ui.tooling.preview.Preview
import com.josancamon19.composemvvmretrofit.data.Address
import com.josancamon19.composemvvmretrofit.data.Company
import com.josancamon19.composemvvmretrofit.data.Geo
import com.josancamon19.composemvvmretrofit.data.User
import com.josancamon19.composemvvmretrofit.ui.ComposeMVVMRetrofitTheme

class MainActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeMVVMRetrofitTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Scaffold(
                        topBar = {
                            appBar()
                        },
                        drawerElevation = 4.dp,
                        drawerContent = {
                            drawerContent()
                        },
                        bodyContent = {
                            usersListObserver(viewModel = viewModel)
                        })
                }
            }
        }
    }
}

@Preview
@Composable
fun appBar() {
    TopAppBar(title = {
        Text("Users")
    }, navigationIcon = {
    }, actions = {
        IconButton(onClick = {}, icon = {
//            Icon(asset = imageResource(id = R.drawable.ic_menu))
        })
    })
}

@Preview
@Composable
fun drawerContent() {
    ListItem(
        text = {
            Text("Users")
        },
        trailing = {
//            Icon(
//                asset = imageResource(id = R.drawable.ic_menu)
//            )
        }
    )
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    usersList(users = getFakeList())
}

@Composable
fun usersListObserver(viewModel: MainViewModel) {
    val users: List<User> by viewModel.users.observeAsState(initial = listOf())
    usersList(users = users)
}

@Composable
fun usersList(users: List<User>) {
    LazyColumnFor(items = users, contentPadding = PaddingValues(all = 16.dp)) { user ->
        Column {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(8.dp)) {
                    Text(user.name)
                    Text(user.email)
                    Text(user.phone)

                }
            }
            Spacer(modifier = Modifier.preferredHeight(8.dp))
        }
    }
}


fun getFakeList(): List<User> {
    val users = mutableListOf<User>()
    for (i in 0..10) {
        users.add(
            User(
                address = Address(
                    city = "Test city",
                    geo = Geo(lat = "123", lng = "321"),
                    street = "street",
                    suite = "suite",
                    zipcode = "zip code"
                ),
                company = Company(
                    bs = "123", catchPhrase = "123", name = "123"
                ),
                email = "joan.santiago.cabezas@gmail.com",
                id = 1,
                name = "Joan Cabezas",
                username = "josancamon19",
                phone = "+57 350 434 2262",
                website = "joancabezas.com"
            )
        )
    }
    return users
}