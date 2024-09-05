package raf.jmijatovic11421rn.RAFVacuumControl.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import raf.jmijatovic11421rn.RAFVacuumControl.model.Permission;
import raf.jmijatovic11421rn.RAFVacuumControl.model.Permissions;
import raf.jmijatovic11421rn.RAFVacuumControl.model.User;
import raf.jmijatovic11421rn.RAFVacuumControl.model.Vacuum;
import raf.jmijatovic11421rn.RAFVacuumControl.services.UserService;
import raf.jmijatovic11421rn.RAFVacuumControl.services.VacuumService;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/vacuums")
@CrossOrigin(origins = {"http://localhost:4200"})
public class VacuumController {

    private VacuumService vacuumService;
    private UserService userService;

    @Autowired
    public VacuumController(VacuumService vacuumService, UserService userService) {
        this.vacuumService = vacuumService;
        this.userService = userService;
    }

    @GetMapping(value="/search")
    public ResponseEntity<?> searchVacuums(@RequestParam(required = false) String name, @RequestParam(required = false) List<String> status, @RequestParam(required = false) Date dateFrom, @RequestParam(required = false) Date dateTo) {
        if (checkPermission(Permissions.can_search_vacuum)) {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.vacuumService.searchVacuums(email, name, status, dateFrom, dateTo));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User does not have the permission to search vacuums");
    }

    @PutMapping(value="/{id}/start")
    public ResponseEntity<?> startVacuum(@PathVariable(required = false) Long id) {
        if (checkPermission(Permissions.can_start_vacuum)) {
            //15 seconds
            this.vacuumService.startVacuum(id); //new thread
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User does not have the permission to start vacuums");
    }

    @PutMapping(value="/{id}/stop")
    public ResponseEntity<?> stopVacuum(@PathVariable(required = false) Long id) {
        if (checkPermission(Permissions.can_stop_vacuum)) {
            //15 seconds
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.vacuumService.stopVacuum(id));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User does not have the permission to stop vacuums");
    }

    @PutMapping(value="/{id}/discharge")
    public ResponseEntity<?> dischargeVacuum(@PathVariable(required = false) Long id) {
        if (checkPermission(Permissions.can_discharge_vacuum)) {
            //30 seconds
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.vacuumService.dischargeVacuum(id));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User does not have the permission to discharge vacuums");
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addVacuum(@Valid @RequestBody Vacuum vacuum) {
        if (checkPermission(Permissions.can_add_vacuum)) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.vacuumService.addVacuum(vacuum));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User does not have the permission to add vacuums");
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> removeVacuum(@PathVariable(required = false) Long id) {
        if (checkPermission(Permissions.can_remove_vacuum)) {
            this.vacuumService.removeVacuum(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User does not have the permission to add vacuums");
    }

    public boolean checkPermission(Permissions permission) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User activeUser = this.userService.findByEmail(email);
        Set<Permission> permissions = activeUser.getPermissions();
        return permissions.contains(new Permission(permission.toString()));
    }

}
