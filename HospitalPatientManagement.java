import java.util.ArrayList;
import java.util.List;

// Abstract class representing a Patient
abstract class Patient implements MedicalRecord {
    private String patientId;
    private String name;
    private int age;
    private String diagnosis;
    private List<String> medicalHistory = new ArrayList<>();

    Patient(String patientId, String name, int age, String diagnosis) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
        this.diagnosis = diagnosis;
    }

    public void getPatientDetails() {
        System.out.println("Patient ID: " + patientId);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
    }

    public void addRecord(String record) {
        medicalHistory.add(record);
    }

    public void viewRecords() {
        System.out.println("Medical Records for " + name + ": " + medicalHistory);
    }

    abstract double calculateBill(); // Must be implemented by subclasses
}

// Interface for managing medical records
interface MedicalRecord {
    void addRecord(String record);
    void viewRecords();
}

// Subclass for In-Patients (Admitted Patients)
class InPatient extends Patient {
    private int daysAdmitted;
    private double dailyCharge;
    private double roomCharge;

    InPatient(String patientId, String name, int age, String diagnosis, int daysAdmitted, double dailyCharge, double roomCharge) {
        super(patientId, name, age, diagnosis);
        this.daysAdmitted = daysAdmitted;
        this.dailyCharge = dailyCharge;
        this.roomCharge = roomCharge;
    }

    @Override
    double calculateBill() {
        return (daysAdmitted * dailyCharge) + roomCharge;
    }
}

// Subclass for Out-Patients (Consultation Only)
class OutPatient extends Patient {
    private double consultationFee;
    private double testCharges;

    OutPatient(String patientId, String name, int age, String diagnosis, double consultationFee, double testCharges) {
        super(patientId, name, age, diagnosis);
        this.consultationFee = consultationFee;
        this.testCharges = testCharges;
    }

    @Override
    double calculateBill() {
        return consultationFee + testCharges;
    }
}

// Main class to test the system
public class HospitalPatientManagement {
    public static void main(String[] args) {
        Patient inpatient = new InPatient("P001", "John Doe", 45, "Pneumonia", 5, 100.0, 500.0);
        Patient outpatient = new OutPatient("P002", "Jane Smith", 30, "Flu", 50.0, 30.0);

        inpatient.addRecord("Admitted for Pneumonia treatment.");
        outpatient.addRecord("Consulted for Flu symptoms.");

        Patient[] patients = {inpatient, outpatient};

        for (Patient p : patients) {
            p.getPatientDetails();
            p.viewRecords();
            System.out.println("Total Bill: $" + p.calculateBill());
            System.out.println("---------------------------");
        }

//        Patient ID: P001
//        Name: John Doe
//        Age: 45
//        Medical Records for John Doe: [Admitted for Pneumonia treatment.]
//        Total Bill: $1000.0
//                ---------------------------
//                Patient ID: P002
//        Name: Jane Smith
//        Age: 30
//        Medical Records for Jane Smith: [Consulted for Flu symptoms.]
//        Total Bill: $80.0
//                ---------------------------
    }
}
