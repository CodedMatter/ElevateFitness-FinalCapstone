package com.techelevator.controller;

import com.techelevator.dao.HydrationDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.FoodIntake;
import com.techelevator.model.Hydration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/hydration")
public class HydrationController {

    @Autowired
    private HydrationDao hydrationDao;

    @Autowired
    private UserDao userDao;

    @PostMapping(path = "")
    public void createHydration(@RequestBody Hydration hydration, Principal principal){
        hydration.setUser_id(userDao.findIdByUsername(principal.getName()));
        hydrationDao.createHydration(hydration);
    }

    @GetMapping(path = "")
    public List<Hydration> getAllHydration(Principal principal){
        return hydrationDao.getAllHydration(userDao.findIdByUsername(principal.getName()));
    }

    @GetMapping(path = "/lastWeek")
    public List<Hydration> getLastWeekHydration(Principal principal) {
        return hydrationDao.getLastWeekHydration(userDao.findIdByUsername(principal.getName()));
    }

}
