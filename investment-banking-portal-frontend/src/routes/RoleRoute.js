import { Navigate } from "react-router-dom";

function RoleRoute({ children }) {
  const token = localStorage.getItem("token");
  if (!token) return <Navigate to="/login" />;

  const role = JSON.parse(atob(token.split(".")[1])).role;
  return role === "ADMIN" ? children : <Navigate to="/deals" />;
}

export default RoleRoute;
