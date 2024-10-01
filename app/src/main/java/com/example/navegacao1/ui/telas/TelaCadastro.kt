package com.example.navegacao1.ui.telas

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.navegacao1.model.dados.Usuario
import com.example.navegacao1.model.dados.UsuarioDAO
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext

@Composable
fun TelaCadastro(
    modifier: Modifier = Modifier,
    onSignupSuccess: () -> Unit  // Navegar de volta para a tela de login após cadastro
) {
    var nome by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    val context = LocalContext.current
    val usuarioDAO = UsuarioDAO()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text("Nome") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = senha,
            onValueChange = { senha = it },
            label = { Text("Senha") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                if (nome.isNotEmpty() && senha.isNotEmpty()) {
                    val usuario = Usuario(nome = nome, senha = senha)
                    usuarioDAO.adicionar(usuario) { success ->
                        if (success) {
                            Toast.makeText(context, "Cadastro realizado com sucesso", Toast.LENGTH_SHORT).show()
                            onSignupSuccess()
                        } else {
                            Toast.makeText(context, "Erro ao cadastrar usuário", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(context, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cadastrar")
        }
    }
}
