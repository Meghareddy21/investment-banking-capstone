import { useState } from "react";
import api from "../api/axios";

import {
  TextField,
  Button,
  Paper,
  Typography,
  Grid,
} from "@mui/material";

function CreateDeal({ onDealCreated }) {
  const [clientName, setClientName] = useState("");
  const [dealType, setDealType] = useState("");
  const [sector, setSector] = useState("");
  const [summary, setSummary] = useState("");

  const submitDeal = async (e) => {
    e.preventDefault();

    await api.post("/deals", {
      clientName,
      dealType,
      sector,
      summary,
    });

    setClientName("");
    setDealType("");
    setSector("");
    setSummary("");

    onDealCreated();
  };

  return (
    <Paper sx={{ padding: 2, marginBottom: 3 }}>
      <Typography variant="subtitle1" gutterBottom>
        Create New Deal
      </Typography>

      <form onSubmit={submitDeal}>
        <Grid container spacing={2}>
          <Grid item xs={12} md={6}>
            <TextField
              size="small"
              fullWidth
              label="Client Name"
              value={clientName}
              onChange={(e) => setClientName(e.target.value)}
              required
            />
          </Grid>

          <Grid item xs={12} md={6}>
            <TextField
              size="small"
              fullWidth
              label="Deal Type (M&A / Equity)"
              value={dealType}
              onChange={(e) => setDealType(e.target.value)}
              required
            />
          </Grid>

          <Grid item xs={12} md={6}>
            <TextField
              size="small"
              fullWidth
              label="Sector"
              value={sector}
              onChange={(e) => setSector(e.target.value)}
              required
            />
          </Grid>

          <Grid item xs={12}>
            <TextField
              size="small"
              fullWidth
              label="Summary"
              multiline
              rows={2}
              value={summary}
              onChange={(e) => setSummary(e.target.value)}
              required
            />
          </Grid>

          <Grid item xs={12}>
            <Button
              variant="contained"
              size="small"
              type="submit"
            >
              Create Deal
            </Button>
          </Grid>
        </Grid>
      </form>
    </Paper>
  );
}

export default CreateDeal;
