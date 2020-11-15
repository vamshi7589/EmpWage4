import java.util.*;
public class EmployeeWageComputation implements IComputeEmpWage
{
	public static final int isFullTime=1;
	public static final int isPartTime=2;

	private int numOfCompany=0;
	private LinkedList<CompanyEmpWage> companyEmpWageList;
	private Map<String,CompanyEmpWage> companyToEmpWageMap;

	public EmployeeWageComputation() {
		companyEmpWageList=new LinkedList<>();
		companyToEmpWageMap=new HashMap<>();
	}

	public void addCompanyEmpWage(String company,int empRatePerHour,int numOfWorkingDays, int maxHoursPerMonth) {

		CompanyEmpWage companyEmpWage=new CompanyEmpWage(company,empRatePerHour,numOfWorkingDays,maxHoursPerMonth);

		companyEmpWageList.add(companyEmpWage);
		companyToEmpWageMap.put(company,companyEmpWage);
	}

	public void computeEmpWage() {
		for (int i=0;i<companyEmpWageList.size(); i++) {
			CompanyEmpWage companyEmpWage = companyEmpWageList.get(i);
			companyEmpWage.setTotalEmpWage(this.computeEmpWage(companyEmpWage));
			System.out.println(companyEmpWage);
		}
	}

	public int computeEmpWage(CompanyEmpWage companyEmpWage) {
		int empHours=0, totalEmpHours=0, totalWorkingDays=0;
		while(totalEmpHours<=companyEmpWage.maxHoursPerMonth && totalWorkingDays<companyEmpWage.numOfWorkingDays) {
			totalWorkingDays++;
			int empCheck=(int) Math.floor(Math.random() *10)%3;
			switch (empCheck) {
				case isFullTime:
					empHours=8;
					break;
				case isPartTime:
                                        empHours=4;
                                        break;
				default:
					empHours=0;
			}
			totalEmpHours+=empHours;
			System.out.println("Day#: "+ totalWorkingDays+" Emp Hr: " +empHours);
		}
		return totalEmpHours*companyEmpWage.empRatePerHour;
	}

	public static void main(String[] args) {
		EmployeeWageComputation e=new EmployeeWageComputation();
		e.addCompanyEmpWage("DMart",20,2,10);
		e.addCompanyEmpWage("Reliance",10,4,20);
		e.computeEmpWage();
	}
}
