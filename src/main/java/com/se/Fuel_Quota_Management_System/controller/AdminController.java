package com.se.Fuel_Quota_Management_System.controller;


import com.se.Fuel_Quota_Management_System.model.Admin;
import com.se.Fuel_Quota_Management_System.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequestMapping
    public class    AdminController
    {
        @Autowired
        private AdminService adminService;



    @PostMapping//(\"/add\")
            public Admin addAdmin(@RequestBody Admin admin) {
            return adminService.addAdmin(admin);
            }
            @GetMapping//(\"/list\")
                    public List<Admin> getAllAdmins() {
                    return adminService.getAllAdmins();
                    }
                    @DeleteMapping//(\"/delete/{id}\")
                            public String deleteAdmin(@PathVariable Long id) {
                            adminService.deleteAdmin(id);
                            return "Admin deleted successfully.";
                            }


    }

