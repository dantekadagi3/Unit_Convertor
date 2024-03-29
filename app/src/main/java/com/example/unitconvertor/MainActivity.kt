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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconvertor.ui.theme.UnitConvertorTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConvertorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                  UnitConvertor()
                }
            }
        }
    }
}
@Composable
fun UnitConvertor(){

    var inputValue by remember{ mutableStateOf("") }
    var outputValue by remember{ mutableStateOf("") }
    var inputUnit by remember{ mutableStateOf("Centimeters") }
    var outputUnit by remember{ mutableStateOf("Meters") }
    var iExpanded by remember{ mutableStateOf(false) }
    var oExpanded by remember{ mutableStateOf(false) }
    var conversionFactor = remember{ mutableStateOf(1.0 ) }
    var oCnversionFactor = remember{ mutableStateOf(1.0 ) }


    fun convertUnit(){
        // ?:-elvis operator
        val inputValueDouble=inputValue.toDoubleOrNull()?:0.0
        val result=(inputValueDouble*conversionFactor.value*100/oCnversionFactor.value).roundToInt()/100
        outputValue=result.toString()

    }


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        //here all the ui elements will be stacked below each other
        Text(
            text = "Unit Convertor",
            color = Color.Black,
            modifier=Modifier,
            style=MaterialTheme.typography.headlineLarge

        )
        Spacer(modifier=Modifier.height(16.dp))
        OutlinedTextField(value =inputValue , onValueChange ={
          inputValue=it
            convertUnit()
        } ,
            label = {Text("Enter Value")})
        Spacer(modifier=Modifier.height(16.dp))

        Row {
            //input Box

          Box {
              //input button
            Button(onClick = {iExpanded=true }) {
                Text(text = inputUnit)
                Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription ="" )
            }
              DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded=false }) {
                  DropdownMenuItem(
                      text = { Text("centimeters") }, onClick = {
                          iExpanded=false
                          inputUnit="Centimeters"
                          conversionFactor.value=0.01
                          convertUnit()
                      }
                  )
                  DropdownMenuItem(
                      text = { Text("Meters") }, onClick = {
                          iExpanded=false
                          inputUnit="Meters"
                          conversionFactor.value=1.0
                          convertUnit()
                      }
                  )
                  DropdownMenuItem(
                      text = { Text("feet") }, onClick = {
                          iExpanded=false
                          inputUnit="feet"
                          conversionFactor.value=0.3048
                          convertUnit()
                      }
                  )
                  DropdownMenuItem(
                      text = { Text("millimeters") }, onClick = {
                          iExpanded=false
                          inputUnit="millimeters"
                          conversionFactor.value=0.001
                          convertUnit()
                      }
                  )
              }
          }
            Box {
                Button(onClick = { oExpanded=true }) {
                    Text(text = outputUnit)
                    Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription ="" )
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded=false }) {
                    DropdownMenuItem(
                        text = { Text("centimeters") }, onClick = {
                            oExpanded=false
                            inputUnit="Centimeters"
                            oCnversionFactor.value=0.01
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Meters") }, onClick = {  oExpanded=false
                            inputUnit="Meters"
                            oCnversionFactor.value=1.00
                            convertUnit() }
                    )
                    DropdownMenuItem(
                        text = { Text("feet") }, onClick = {
                            oExpanded=false
                            inputUnit="feet"
                            oCnversionFactor.value=0.3048
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("millimeters") }, onClick = {
                            oExpanded=false
                            inputUnit="millimeters"
                            oCnversionFactor.value=0.001
                            convertUnit()
                        }
                    )

            }
         //here all the ui elements will be stacked beside each other

        }

    }
        Spacer(modifier=Modifier.height(16.dp))
        Text("Result $outputValue  $outputUnit",
            style=MaterialTheme.typography.headlineMedium)
}}
@Preview(showBackground = true)
@Composable
fun prevUnitConvertor(){
    UnitConvertor()
}
