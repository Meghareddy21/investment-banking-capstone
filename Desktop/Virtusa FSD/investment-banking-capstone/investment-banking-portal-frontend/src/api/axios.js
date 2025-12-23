import axios from "axios";

const api = axios.create({
  baseURL: "http://localhost:8080/api",
});

// Add JWT to headers for protected APIs ONLY
api.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem("token");

    // IMPORTANT: Do NOT add token for login
    if (token && !config.url.includes("/auth/login")) {
      config.headers.Authorization = `Bearer ${token}`;
    }

    return config;
  },
  (error) => Promise.reject(error)
);

export default api;
