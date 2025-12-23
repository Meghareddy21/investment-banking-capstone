package com.virtusa.pipeline.deal;

import com.virtusa.pipeline.auth.JwtUtil;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deals")
public class DealController {

    private final DealRepository dealRepository;

    public DealController(DealRepository dealRepository) {
        this.dealRepository = dealRepository;
    }

    // ✅ CREATE DEAL
    @PostMapping
    public Deal createDeal(@RequestBody Deal deal) {
        deal.setCurrentStage("Prospect");
        return dealRepository.save(deal);
    }
    @PutMapping("/{id}")
    public Deal updateDeal(@PathVariable String id,
                           @RequestBody Deal updatedDeal) {

        Deal deal = dealRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Deal not found"));

        deal.setSummary(updatedDeal.getSummary());
        deal.setSector(updatedDeal.getSector());
        deal.setDealType(updatedDeal.getDealType());

        return dealRepository.save(deal);
    }


    // ✅ GET ALL DEALS (ROLE BASED)
    @GetMapping
    public List<Deal> getAllDeals(
            @RequestHeader(value = "Authorization", required = false) String authHeader) {

        List<Deal> deals = dealRepository.findAll();

        // If no Authorization header, return deals as-is
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return deals;
        }

        // Extract token safely
        String token = authHeader.substring(7);

        // Extract role from JWT
        String role = JwtUtil.extractRole(token);
        System.out.println("ROLE FROM JWT = " + role);

        // Hide dealValue for USER
        if ("USER".equals(role)) {
            deals.forEach(deal -> deal.setDealValue(null));
        }

        return deals;
    }
    
    @PatchMapping("/{id}/stage")
    public Deal updateStage(
            @PathVariable String id,
            @RequestParam String stage) {

        Deal deal = dealRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Deal not found"));

        deal.setCurrentStage(stage);
        return dealRepository.save(deal);
    }
    @PostMapping("/{id}/notes")
    public Deal addNote(@PathVariable String id,
                        @RequestBody String note) {

        Deal deal = dealRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Deal not found"));

        deal.getNotes().add(note);
        return dealRepository.save(deal);
    }
    
   
    
    @DeleteMapping("/{id}")
    public String deleteDeal(
            @PathVariable String id,
            @RequestHeader("Authorization") String authHeader) {

        String token = authHeader.substring(7); // remove Bearer
        String role = JwtUtil.extractRole(token);

        if (!"ADMIN".equals(role)) {
            throw new RuntimeException("Only ADMIN can delete deals");
        }

        dealRepository.deleteById(id);
        return "Deal deleted successfully";
    }
    @GetMapping("/{id}")
    public Deal getDealById(@PathVariable String id) {

        return dealRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Deal not found"));
    }
    
    @PatchMapping("/{id}/value")
    public Deal updateDealValue(
            @PathVariable String id,
            @RequestParam Long value) {

        Deal deal = dealRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Deal not found"));

        deal.setDealValue(value);
        return dealRepository.save(deal);
    }






}
