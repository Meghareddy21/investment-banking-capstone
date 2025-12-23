import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from "./auth/Login";
import DealList from "./deals/DealList";
import PrivateRoute from "./routes/PrivateRoute";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/login" element={<Login />} />

        <Route
          path="/deals"
          element={
            <PrivateRoute>
              <DealList />
            </PrivateRoute>
          }
        />

        <Route path="*" element={<Login />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
