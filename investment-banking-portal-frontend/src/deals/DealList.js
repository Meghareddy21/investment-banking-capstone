import { useEffect, useState } from "react";
import api from "../api/axios";
import CreateDeal from "./CreateDeal";

import {
  AppBar,
  Toolbar,
  Typography,
  Button,
  Grid,
  Box,
  Card,
  CardContent,
} from "@mui/material";

function DealList() {
  const [deals, setDeals] = useState([]);

  // Load deals from backend
  const loadDeals = () => {
    api
      .get("/deals")
      .then((res) => setDeals(res.data))
      .catch(() => alert("Failed to load deals"));
  };

  useEffect(() => {
    loadDeals();
  }, []);

  // Get role from JWT
  const token = localStorage.getItem("token");
  const role = token
    ? JSON.parse(atob(token.split(".")[1])).role
    : "";

  // Deal stages
  const stages = [
    "Prospect",
    "UnderEvaluation",
    "TermSheetSubmitted",
    "Closed",
    "Lost",
  ];

  // Group deals by stage
  const groupedDeals = stages.reduce((acc, stage) => {
    acc[stage] = deals.filter((d) => d.currentStage === stage);
    return acc;
  }, {});

  return (
    <>
      {/* TOP HEADER */}
      <AppBar position="static">
        <Toolbar>
          <Typography variant="h6" sx={{ flexGrow: 1 }}>
            Investment Banking Deal Pipeline
          </Typography>

          <Button
            color="inherit"
            onClick={() => {
              localStorage.removeItem("token");
              window.location.href = "/login";
            }}
          >
            Logout
          </Button>
        </Toolbar>
      </AppBar>

      <Box sx={{ padding: 3 }}>
        {/* CREATE DEAL FORM (ADMIN ONLY) */}
        {role === "ADMIN" && (
          <CreateDeal onDealCreated={loadDeals} />
        )}

        {/* PIPELINE */}
        <Grid container spacing={2}>
          {stages.map((stage) => (
            <Grid item xs={12} md={2.4} key={stage}>
              <Box
                sx={{
                  backgroundColor: "#f4f6f8",
                  padding: 1.5,
                  minHeight: "280px",
                  borderRadius: 2,
                }}
              >
                <Typography
                  variant="h6"
                  align="center"
                  gutterBottom
                >
                  {stage}
                </Typography>

                {groupedDeals[stage].map((d) => (
                  <Card key={d.id} sx={{ marginBottom: 2 }}>
                    <CardContent sx={{ padding: 1.5 }}>
  <Typography variant="subtitle2" fontWeight="bold">
    {d.clientName}
  </Typography>

  <Typography variant="caption">
    {d.dealType} | {d.sector}
  </Typography>

  {role === "ADMIN" && (
    <Typography
      variant="caption"
      color="primary"
      display="block"
    >
      Value: {d.dealValue}
    </Typography>
  )}
</CardContent>

                  </Card>
                ))}
              </Box>
            </Grid>
          ))}
        </Grid>
      </Box>
    </>
  );
}

export default DealList;
