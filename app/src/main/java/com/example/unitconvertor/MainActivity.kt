package com.example.unitconvertor

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconvertor.ui.theme.UnitConvertorTheme
import java.time.format.TextStyle
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConvertorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
                    color = Color.White
                ) {
                    UnitConvertor()
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnitConvertor(){
    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meters") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    val conversionFactor = remember{ mutableStateOf(1.0) }
    val oConversionFactor = remember{ mutableStateOf(1.0) }

    fun convertUnits(){
        val inputValueDouble = inputValue.toDoubleOrNull()?:0.0
        val Result = (inputValueDouble * conversionFactor.value * 100.0 / oConversionFactor.value).roundToInt() / 100.0
        outputValue = Result.toString()
    }
    val customStyle = androidx.compose.ui.text.TextStyle(
        fontFamily =  FontFamily.Cursive,
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Blue
    )
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val context = LocalContext.current
        Text("Unit Convertor",style = customStyle)
        Spacer(modifier = Modifier.height(16.dp))
//        OutlinedTextField(value = inputValue, onValueChange = {
//            //will us anonymous function here
//            inputValue = it
//        },
//        label = Text(text = "Enter value"))
        OutlinedTextField(value = inputValue, onValueChange = {
            inputValue = it
            convertUnits()
        },
        label = {Text(text = "Enter value")})
        Spacer(modifier = Modifier.height(16.dp))
        Row {
/*            Button( // this is the part to describe the click event for th button
//                onClick = {Toast.makeText(context,"Button clicked!",Toast.LENGTH_SHORT).show()}
//            )
//            {
//                // this is to show the button name/text
//                Text("click me!")
//            }  */
            Box {
                // Input box
                Button(onClick = { iExpanded = true}) {
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                    DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false}) {
                        DropdownMenuItem(text = {Text("Centimeters")}, onClick = {
                            iExpanded = false
                            inputUnit = "Centimeters"
                            conversionFactor.value = 0.01
                            convertUnits()
                        })
                        DropdownMenuItem(text = {Text("meters")}, onClick = {
                            iExpanded = false
                            inputUnit = "Meters"
                            conversionFactor.value = 1.0
                            convertUnits()
                        })
                        DropdownMenuItem(text = {Text("Kilometers")}, onClick = {
                            iExpanded = false
                            inputUnit = "Kilometers"
                            conversionFactor.value = 1000.0
                            convertUnits()
                        })
                        DropdownMenuItem(text = {Text("Milimeters")}, onClick = {
                            iExpanded = false
                            inputUnit = "Milimeters"
                            conversionFactor.value = 0.001
                            convertUnits()
                        })
                        DropdownMenuItem(text = {Text("Inch")}, onClick = {
                            iExpanded = false
                            inputUnit = "Inch"
                            conversionFactor.value = 0.0254
                            convertUnits()
                        })
                        DropdownMenuItem(text = {Text("Feet")}, onClick = {
                            iExpanded = false
                            inputUnit = "Feet"
                            conversionFactor.value = 0.3048
                            convertUnits()
                        })
                    }
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box {
                // Output box
                Button(onClick = { oExpanded = true }) {
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                    DropdownMenu(expanded = oExpanded, onDismissRequest = { iExpanded = false}) {
                        DropdownMenuItem(text = {Text("Centimeters")}, onClick = {
                            oExpanded = false
                            outputUnit = "Centimeters"
                            oConversionFactor.value = 0.01
                            convertUnits()
                        })
                        DropdownMenuItem(text = {Text("meters")}, onClick = {
                            oExpanded = false
                            outputUnit = "Meters"
                            oConversionFactor.value = 1.0
                            convertUnits()
                        })
                        DropdownMenuItem(text = {Text("Kilometers")}, onClick = {
                            oExpanded = false
                            outputUnit = "Kilometers"
                            oConversionFactor.value = 1000.0
                            convertUnits()
                        })
                        DropdownMenuItem(text = {Text("Milimeters")}, onClick = {
                            oExpanded = false
                            outputUnit = "Milimeters"
                            oConversionFactor.value = 0.001
                            convertUnits()
                        })
                        DropdownMenuItem(text = {Text("Inch")}, onClick = {
                            oExpanded = false
                            outputUnit = "Inch"
                            oConversionFactor.value = 0.0254
                            convertUnits()
                        })
                        DropdownMenuItem(text = {Text("Feet")}, onClick = {
                            oExpanded = false
                            outputUnit = "feet"
                            oConversionFactor.value = 0.3048
                            convertUnits()
                        })
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Result : $outputValue $outputUnit",
            style = MaterialTheme.typography.headlineMedium)
    }
}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "$name",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun anyfucntionPreview() {
    UnitConvertor()

}