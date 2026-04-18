<template>
  <v-container class="login-container">
    <v-card class="pa-6 login-card">
      <div class="logo-container">
        <img src="/frutero.png" class="logo"/>
      </div>

      <h2 class="mb-4 text-center">Inicio de sesión</h2>

      <v-text-field
        v-model="email"
        label="Correo"
        outlined
        dense
      />

      <v-text-field
        v-model="password"
        :type="showPassword ? 'text' : 'password'"
        label="Contraseña"
        :append-icon="showPassword ? 'mdi-eye-off' : 'mdi-eye'"
        @click:append="showPassword = !showPassword"
        outlined
        dense
      />

      <v-btn
        color="green"
        block
        class="mt-4"
        :loading="loading"
        :disabled="loading"
        @click="login"
      >
        Entrar
      </v-btn>

      <v-btn text color="green" @click="mostrarRegistro = true">
        Crear cuenta
      </v-btn>

      <v-alert v-if="errorMessage" type="error" class="mt-4" dense outlined>
        {{ errorMessage }}
      </v-alert>

      <v-alert v-if="successMessage" type="success" class="mt-4" dense outlined>
        {{ successMessage }}
      </v-alert>
    </v-card>

    <v-dialog v-model="mostrarRegistro" max-width="400">
      <v-card>
        <v-card-title>Crear Nueva Cuenta</v-card-title>
        <v-card-text>
          <v-text-field v-model="nuevoUsuario.nombre" label="Nombre" />
          <v-text-field v-model="nuevoUsuario.email" label="Correo" />
          <v-text-field v-model="nuevoUsuario.password" label="Contraseña" type="password" />
        </v-card-text>
        <v-card-actions>
          <v-spacer />
          <v-btn text color="grey" @click="mostrarRegistro = false">Cancelar</v-btn>
          <v-btn color="green" @click="registrarUsuario">Registrarse</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

    <v-dialog v-model="dialog" max-width="400">
      <v-card class="pa-6 text-center" color="#e8f5e9" outlined>
        <v-icon size="64" color="green">mdi-check-circle</v-icon>
        <h2 class="mt-4">{{ dialogMessage }}</h2>
        <v-btn color="green" class="mt-4" @click="dialog = false">Cerrar</v-btn>
      </v-card>
    </v-dialog>
  </v-container>
</template>

<script>
export default {
  data() {
    return {
      // CONFIGURACIÓN DE URL (CAMBIA ESTO)
      // Pon la URL de tu App Service de Azure aquí:
      urlAzure: "https://tu-nombre-de-backend.azurewebsites.net",
      
      email: "",
      password: "",
      showPassword: false,
      loading: false,
      errorMessage: "",
      successMessage: "",
      mostrarRegistro: false,
      nuevoUsuario: { nombre: "", email: "", password: "" },
      dialog: false,
      dialogMessage: ""
    };
  },

  computed: {
    // Esta función elige automáticamente entre localhost o Azure
    baseUrl() {
      return window.location.hostname === "localhost" 
        ? "http://localhost:8081" 
        : this.urlAzure;
    }
  },

  methods: {
    redirectUser(rol) {
      if (rol === "admin") {
        this.$router.push("/admin");
      } else {
        this.$router.push("/");
      }
    },

    async login() {
      this.errorMessage = "";
      this.successMessage = "";
      this.loading = true;

      try {
        const res = await fetch(`${this.baseUrl}/auth/login`, {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify({
            email: this.email.trim(),
            password: this.password.trim()
          })
        });

        const data = await res.json();
        if (!res.ok) throw new Error(data.message || "Error en credenciales");

        localStorage.setItem("user", JSON.stringify(data.user));
        this.successMessage = "Login correcto";
        setTimeout(() => this.redirectUser(data.user.rol), 500);

      } catch (err) {
        this.errorMessage = "Error: " + err.message;
      } finally {
        this.loading = false;
      }
    },

    async registrarUsuario() {
      try {
        const res = await fetch(`${this.baseUrl}/auth/register`, {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(this.nuevoUsuario)
        });

        const data = await res.json();
        if (!res.ok) throw new Error(data.message || "Error al registrar");

        this.dialogMessage = "👤 Usuario creado con éxito";
        this.dialog = true;
        this.mostrarRegistro = false;
        this.nuevoUsuario = { nombre: "", email: "", password: "" };

      } catch (err) {
        this.dialogMessage = "❌ " + err.message;
        this.dialog = true;
      }
    }
  }
};
</script>

<style scoped>
.login-container { height: 100vh; display: flex; justify-content: center; align-items: center; background: #e8f5e9; }
.login-card { width: 100%; max-width: 400px; text-align: center; border-radius: 16px; box-shadow: 0 10px 30px rgba(0,0,0,0.1); }
.logo-container { display: flex; justify-content: center; margin-bottom: 25px; }
.logo { width: 140px; animation: float 3s ease-in-out infinite; }
@keyframes float {
  0% { transform: translateY(0px); }
  50% { transform: translateY(-12px); }
  100% { transform: translateY(0px); }
}
</style>
