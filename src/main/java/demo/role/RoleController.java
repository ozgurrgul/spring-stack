package demo.role;

import demo.common.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Created by ozgur on 7/5/17.
 */
@RestController
@RequestMapping("/admin/role")
public class RoleController {

    @Autowired
    RoleRepository roleRepository;

    public RoleController(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    //--------------------- LIST ALL ROLE ---------------------//
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Role> listAllRoles(Authentication authentication) {
        return roleRepository.findAll();
    }

    //--------------------- CREATE NEW ROLE ---------------------//
    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ADMIN')")
    Object createNewRole(@RequestBody Role role) {

        try {
            roleRepository.save(role);
        } catch (DataIntegrityViolationException e) {
            throw new BadRequestException("User already exists in database");
        }

        return null;
    }

    //--------------------- DELETE ROLE ---------------------//
    @RequestMapping(method = RequestMethod.DELETE)
    @PreAuthorize("hasAuthority('ADMIN')")
    Object deleteRole(@RequestBody Role role) {

        try {
            roleRepository.delete(role);
        } catch (DataIntegrityViolationException e) {
            throw new BadRequestException("Error when deleting role");
        }

        return null;
    }

    //--------------------- UPDATE ROLE ---------------------//
    @RequestMapping(method = RequestMethod.PUT)
    @PreAuthorize("hasAuthority('ADMIN')")
    Object updateRole(@RequestBody Role role) {

        Optional<Role> toUpdate = roleRepository.findById(role.id);

        if(toUpdate.isPresent() == false) {
            throw new BadRequestException("Role doesn't exist");
        }

        Role tmpRole = toUpdate.get();
        tmpRole.roleName = role.roleName;

        try {
            roleRepository.save(tmpRole);
        } catch (DataIntegrityViolationException e) {
            throw new BadRequestException("Error when updating role");
        }

        return null;
    }
}