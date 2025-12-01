package com.example.customcounter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.customcounter.ui.theme.CustomCounterTheme
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomCounterTheme() {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    EstadoElevadoDemo()
                }
            }
        }
    }
}

// =============================================================
// TODO EL CÓDIGO COMPOSABLE DEL EJEMPLO VA AQUÍ ABAJO
// =============================================================

@Composable
fun EstadoElevadoDemo() {
    var contador by remember { mutableStateOf(0) }
    var estaActivo by remember { mutableStateOf(true) }

    ContadorScreen(
        contador = contador,
        estaActivo = estaActivo,
        onIncrementar = { contador++ },
        onDecrementar = { if (contador > 0) contador-- },
        onReset = { contador = 0 },
        onToggleActivo = { estaActivo = !estaActivo }
    )
}

@Composable
fun ContadorScreen(
    contador: Int,
    estaActivo: Boolean,
    onIncrementar: () -> Unit,
    onDecrementar: () -> Unit,
    onReset: () -> Unit,
    onToggleActivo: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = if (estaActivo) "Contador: $contador" else "Contador desactivado",
            style = MaterialTheme.typography.headlineMedium
        )

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(onClick = onDecrementar, enabled = estaActivo) {
                Text("-")
            }
            Button(onClick = onIncrementar, enabled = estaActivo) {
                Text("+")
            }
            Button(onClick = onReset) {
                Text("Reset")
            }
        }

        SwitchConEtiqueta(
            checked = estaActivo,
            onCheckedChange = onToggleActivo
        )

        Text(
            text = "Este texto es estático y no se recompone innecesariamente",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun SwitchConEtiqueta(checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text("Activar contador")
        Spacer(Modifier.width(12.dp))
        Switch(checked = checked, onCheckedChange = onCheckedChange)
    }
}

// =============================================================
// Preview (opcional, pero muy útil)
// =============================================================

@Preview(showBackground = true, name = "Demo Contador")
@Composable
fun PreviewContador() {
    CustomCounterTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            EstadoElevadoDemo()
        }
    }
}