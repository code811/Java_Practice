interface Approvable {
    boolean approve(String approverName);
    boolean deny(String approverName, String reason);
}

abstract class LeaveRequest implements Approvable {

    private final int requestId;
    private final Employee employee;
    private String startDate;
    private String endDate;
    private String status = "Pending";    // "Pending", "Approved", "Denied"
    private String reason;

    protected LeaveRequest(int requestId, Employee employee, String startDate, String endDate, String reason) {
        this.requestId = requestId;
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
    }

    protected int calculateLeaveDays() {
        return DateService.getDuration(getStartDate(), getEndDate());
    }

    protected int getRemainingBalance(int requestAmount) {
        return employee.getLeaveBalance() - requestAmount;
    }

    protected int getRequestId() {
        return requestId;
    }

    protected Employee getEmployee() {
        return employee;
    }

    protected String getStartDate() {
        return startDate;
    }

    protected void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    protected String getEndDate() {
        return endDate;
    }

    protected void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    protected String getStatus() {
        return status;
    }

    protected void setStatus(String status) {
        this.status = status;
    }

    protected String getReason() {
        return reason;
    }

    protected void setReason(String reason) {
        this.reason = reason;
    }

    public abstract boolean processRequest();
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
    public boolean approve(String approverName) {
        if(!approverName.equals("Howard")) {
            System.out.println("Access level is not high enough to manually approve request");
            return false;
        }

        System.out.println("The sick leave request for " + getEmployee().getName() + " has been manually approved");
        setStatus("Approved");
        return true;
    }

    @Override
    public boolean deny(String approverName, String reason) {
        if(!approverName.equals("Howard")) {
            System.out.println("Access level is not high enough to approve request");
            return false;
        }

        System.out.println("The sick leave request for " + getEmployee().getName() + " has been denied due to as followed:");
        System.out.println(reason);
        setStatus("Denied");
        return true;
    }

    @Override
    public boolean processRequest() {
        int timeOff = calculateLeaveDays();

        if(!medicalCertificateProvided && timeOff > 2) {
            System.out.println("Sick leave longer than 2 days requires a medical certificate");
            return false;
        } else if(timeOff > getEmployee().getLeaveBalance()) {
            System.out.println("Employee " + getEmployee().getName() + " does not have enough PTO to cover the entire duration");
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
    public boolean approve(String approverName) {
        if(!approverName.equals("Howard")) {
            System.out.println("Access level is not high enough to manually approve request");
            return false;
        }

        System.out.println("The vacation leave request for " + getEmployee().getName() + " has been approved");
        setStatus("Approved");
        return true;
    }

    @Override
    public boolean deny(String approverName, String reason) {
        if(!approverName.equals("Howard")) {
            System.out.println("Access level is not high enough to manually approve request");
            return false;
        }

        System.out.println("The vacation leave request for " + getEmployee().getName() + " has been denied due to as followed:");
        System.out.println(reason);
        setStatus("Denied");
        return true;
    }

    @Override
    public boolean processRequest() {
        if(isPaidLeave() && DateService.getDuration(getStartDate(), getEndDate()) > getEmployee().getLeaveBalance()) {
            System.out.println("Employee " + getEmployee().getName() + " does not have enough PTO to cover the entire duration");
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
    public boolean approve(String approverName) {
        if(!approverName.equals("Howard")) {
            System.out.println("Access level is not high enough to manually approve request");
            return false;
        }

        System.out.println("The maternity leave request for " + getEmployee().getName() + " has been approved");
        setStatus("Approved");
        return true;
    }

    @Override
    public boolean deny(String approverName, String reason) {
        if(!approverName.equals("Howard")) {
            System.out.println("Access level is not high enough to manually approve request");
            return false;
        }

        System.out.println("The maternity leave request for " + getEmployee().getName() + " has been denied due to as followed:");
        System.out.println(reason);
        setStatus("Denied");
        return true;
    }

    @Override
    public boolean processRequest() {
        if(!isFmlaEligible()) {
            System.out.println("Must meet FMLA requirements to be eligible for short-term disability");
            return false;
        } else if(DateService.getDuration(getStartDate(), getEndDate()) > 84) {
            System.out.println("FMLA only covers 12 weeks for paid time off");
            return false;
        }
        System.out.println("Processing maternity leave request...");
        return true;
    }
}