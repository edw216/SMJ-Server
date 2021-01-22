package com.experiencers.server.smj.admin;

import com.experiencers.server.smj.admin.Admin;
import com.experiencers.server.smj.admin.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public Admin loginCheck(Admin admin) {
        Admin login = adminRepository.save(admin);

        return login;
    }
}
