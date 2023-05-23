package demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin-staff")
public class AdministrativeStaffController {

    private List<AdministrativeStaff> staffMembers = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<AdministrativeStaff>> getAllStaffMembers() {
        return ResponseEntity.ok(staffMembers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdministrativeStaff> getStaffMemberById(@PathVariable int id) {

        AdministrativeStaff staffMember = findStaffMemberById(id);
        if (staffMember != null) {
            return ResponseEntity.ok(staffMember);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<AdministrativeStaff> createStaffMember(@RequestBody AdministrativeStaff staffMember) {

        staffMembers.add(staffMember);
        return ResponseEntity.status(HttpStatus.CREATED).body(staffMember);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdministrativeStaff> updateStaffMember(
            @PathVariable int id, @RequestBody AdministrativeStaff updatedStaffMember) {

        AdministrativeStaff staffMember = findStaffMemberById(id);
        if (staffMember != null) {
            staffMember.setName(updatedStaffMember.getName());
            staffMember.setRole(updatedStaffMember.getRole());
            return ResponseEntity.ok(staffMember);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStaffMember(@PathVariable int id) {

        AdministrativeStaff staffMember = findStaffMemberById(id);
        if (staffMember != null) {
            staffMembers.remove(staffMember);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private AdministrativeStaff findStaffMemberById(int id) {

        for (AdministrativeStaff staffMember : staffMembers) {
            if (staffMember.getId() == id) {
                return staffMember;
            }
        }
        return null;
    }
}

