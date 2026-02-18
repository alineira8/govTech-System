

import model.*;

public class Main{
    public static void main(String[] args){

        Citizen citizen = new Citizen("264566", "gyfhg", "amgf@gmail.com");
        System.out.println(citizen.getCitizenId());

        BirthCertificateService birthCertificateService = new BirthCertificateService ();
        System.out.println(birthCertificateService.getServiceId() + " " + birthCertificateService.getServiceName() + " " + birthCertificateService.getFee());

        DrivingTestService  drivingTestService = new  DrivingTestService ("134");
        System.out.println( drivingTestService.getServiceId() + " " + drivingTestService.getServiceName() + " " + drivingTestService.getFee());
    }
}
