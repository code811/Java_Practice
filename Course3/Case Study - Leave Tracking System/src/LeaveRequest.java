class LeaveRequest {

    private final int requestId;
    private final Employee employee;
    private String startDate;
    private String endDate;
    private String status = "Pending";    // "Pending", "Approved", "Denied"
    private String reason;

    public LeaveRequest(int requestId, Employee employee, String startDate, String endDate, String reason) {
        this.requestId = requestId;
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
    }

    public boolean processRequest() {
        System.out.println("Processing generic leave request...");
        return true;
    }

    public int getRemainingBalance(int requestAmount) {
        return employee.getLeaveBalance() - requestAmount;
    }

    public int getRequestId() {
        return requestId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}

class SickLeaveRequest extends LeaveRequest {

    private boolean medicalCertificateProvided;

    public SickLeaveRequest(int requestId, Employee employee,
                            String startDate, String endDate,
                            boolean medicalCertificateProvided) {
        super(requestId, employee, startDate, endDate, "Sick Leave");
        this.medicalCertificateProvided = medicalCertificateProvided;
    }

    public boolean isMedicalCertificateProvided() {
        return medicalCertificateProvided;
    }

    @Override
    public boolean processRequest() {
        if(!medicalCertificateProvided && DateService.getDuration(super.getStartDate(), super.getEndDate()) > 2) {
            System.out.println("Sick leave longer than 2 days requires a medical certificate");
            return false;
        }
        System.out.println("Processing sick leave request...");
        return true;
    }
}


class VacationLeaveRequest extends LeaveRequest {

    private boolean paidLeave;

    public VacationLeaveRequest(int requestId, Employee employee,
                                String startDate, String endDate,
                                boolean paidLeave) {
        super(requestId, employee, startDate, endDate, "Vacation Time");
        this.paidLeave = paidLeave;
    }

    public boolean isPaidLeave() {
        return paidLeave;
    }

    @Override
    public boolean processRequest() {
        if(isPaidLeave() && DateService.getDuration(super.getStartDate(), super.getEndDate()) > super.getEmployee().getLeaveBalance()) {
            System.out.println("Employee " + super.getEmployee().getName() + " does not have enough PTO to cover the entire duration");
            return false;
        }
        System.out.println("Processing vacation time request...");
        return true;
    }
}


class MaternityLeaveRequest extends LeaveRequest {

    private boolean fmlaEligible;

    public MaternityLeaveRequest(int requestId, Employee employee,
                                 String startDate, String endDate,
                                 boolean fmlaEligible) {
        super(requestId, employee, startDate, endDate, "Maternity Leave");
        this.fmlaEligible = fmlaEligible;
    }

    public boolean isFmlaEligible() {
        return fmlaEligible;
    }

    @Override
    public boolean processRequest() {
        if(!isFmlaEligible()) {
            System.out.println("Must meet FMLA requirements to be eligible for short-term disability");
            return false;
        }
        System.out.println("Processing maternity leave request...");
        return true;
    }
}