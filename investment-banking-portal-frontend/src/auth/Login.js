import { useForm } from "react-hook-form";
import { Button, TextField, Container } from "@mui/material";
import api from "../api/axios";
import { useNavigate } from "react-router-dom";

function Login() {
  const { register, handleSubmit } = useForm();
  const navigate = useNavigate();

  const onSubmit = async (data) => {
  const res = await api.post("/auth/login", data);

  // Even if Axios throws warning, backend returned JWT
  if (res && res.data) {
    localStorage.setItem("token", res.data);
    navigate("/deals");
  }
};



  return (
    <Container maxWidth="sm">
      <h2>Login</h2>

      <form onSubmit={handleSubmit(onSubmit)}>
        <TextField
          label="Username"
          fullWidth
          margin="normal"
          {...register("username", { required: true })}
        />

        <TextField
          label="Password"
          type="password"
          fullWidth
          margin="normal"
          {...register("password", { required: true })}
        />

        <Button type="submit" variant="contained">
          Login
        </Button>
      </form>
    </Container>
  );
}

export default Login;
