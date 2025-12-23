import { render, screen } from "@testing-library/react";
import Login from "./Login";

// MOCK AXIOS (VERY IMPORTANT)
jest.mock("../api/axios", () => ({
  post: jest.fn(() => Promise.resolve({ data: "fake-jwt-token" })),
}));

test("renders login button", () => {
  render(<Login />);
  const button = screen.getByRole("button", { name: /login/i });
  expect(button).toBeInTheDocument();
});
