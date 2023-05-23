package demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/nurses")
public class NurseController {

    private List<Nurse> nurses = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<Nurse>> getAllNurses() {
        return ResponseEntity.ok(nurses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Nurse> getNurseById(@PathVariable int id) {

        Nurse nurse = findNurseById(id);
        if (nurse != null) {
            return ResponseEntity.ok(nurse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Nurse> createNurse(@RequestBody Nurse nurse) {

        nurses.add(nurse);
        return ResponseEntity.status(HttpStatus.CREATED).body(nurse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Nurse> updateNurse(@PathVariable int id, @RequestBody Nurse updatedNurse) {

        Nurse nurse = findNurseById(id);
        if (nurse != null) {
            nurse.setName(updatedNurse.getName());
            nurse.setDepartment(updatedNurse.getDepartment());
            return ResponseEntity.ok(nurse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNurse(@PathVariable int id) {

        Nurse nurse = findNurseById(id);
        if (nurse != null) {
            nurses.remove(nurse);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private Nurse findNurseById(int id) {
        // Implement logic to find nurse by ID
        for (Nurse nurse : nurses) {
            if (nurse.getId() == id) {
                return nurse;
            }
        }
        return null;
    }
}
