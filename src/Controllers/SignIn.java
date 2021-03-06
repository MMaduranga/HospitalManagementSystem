package Controllers;

import javax.swing.JFrame;
import java.io.BufferedReader;
import java.io.FileReader;
import View.AdminInterface.AdminDashboardInterface;
import View.MedicalOfficerInterface.MedicalOfficerDashboardInterface;
import View.PateintInterface.PatientDashboardInterface;
import View.ReceptionistInterface.ReceptionistDashboardInterface;

import java.io.IOException;

public class SignIn {

    private String strUserName;
    private String strPassword;
    private String strUserMode;

    private String[] filePath = {"src\\TxtFiles\\Admin.mov", "src\\TxtFiles\\MedicalOfficer.mov", ""
        + "src\\TxtFiles\\Pateint.mov", "src\\TxtFiles\\Receptionist.mov"};

    public SignIn(String userName, String password, String userMode) {
        setUserName(userName);//get user input add save it to veriable
        setPassword(password);//get user input add save it to veriable
        setUserMode(userMode);//get user input add save it to veriable

    }

    //encapsulation
    public void setUserName(String userName) {
        this.strUserName = userName;//save user name to username veriable
    }

    public void setPassword(String password) {
        this.strPassword = password;//save password to password veriable
    }

    public void setUserMode(String userMode) {
        this.strUserMode = userMode;//save user mode to usermode veriable
    }

    public String getUserName() {
        return this.strUserName;//return user name value
    }

    public String getPassword() {
        return this.strPassword;//return password value
    }

    public String getUserMode() {
        return this.strUserMode;//return user mode value
    }

    public String[] getFilePath() {
        return this.filePath;
    }

    //other methods
    public JFrame compare() {//validate user information
        JFrame usersFrame = null;
        FileSecurity fileSecurity = new FileSecurity(selectUserMode());
        try {//to catch the error

            FileReader readUserFile = new FileReader(fileSecurity.setFilePathToTxt());//open users file to read
            BufferedReader readFile = new BufferedReader(readUserFile);

            String strLine = readFile.readLine();//save 1st line of users file to a string

            while (strLine != null) {//compare the line is not equals to null
                String strNextLine = readFile.readLine();

                if (this.getUserMode().equals("Admin")) {

                    if (strLine.charAt(0) == '|') {

                        String[] strAdminDetails = strLine.split("~");

                        if (CompareUserNameAndPassword(strAdminDetails[1], strAdminDetails[2])) {
                            usersFrame = chooseFrame(strLine);
                            break;
                        }
                    }
                } else {
                    if (strLine.charAt(0) == '|' && strNextLine != null && strNextLine.charAt(0) == '|'
                            || strLine.charAt(0) == '|' && strNextLine == null) {//check the validation of 1st and 2nd lines 
                        String usersDetails[] = strLine.split("~");//users details stored in this array
                       
                        if (CompareUserNameAndPassword(usersDetails[1], usersDetails[2])) {
                            usersFrame = chooseFrame(strLine);
                            break;

                        }

                    } else if (strLine.charAt(0) == '|' && strNextLine != null && strNextLine.charAt(0) != '|') {//check the validation of 1st and 2nd lines 
                        String usersDetails[] = (strLine + strNextLine).split("~");//visitor details stored in this array
                        if (CompareUserNameAndPassword(usersDetails[1], usersDetails[2])) {
                            usersFrame = chooseFrame(strLine + strNextLine);
                            break;

                        }
                        strNextLine = readFile.readLine();//save next line to continue the loop
                    }
                }
                strLine = strNextLine;//save next line of the txt file to rotate
            }
            readUserFile.close();//close the opened file
            readFile.close();

        } catch (IOException e) {

        }
        fileSecurity.setFilePathMOV();
        return usersFrame; //return true or false by compareing the user name and password validation
    }

    public boolean CompareUserNameAndPassword(String userName, String Password) {
        if (this.getUserName().equals(userName) && this.getPassword().equals(Password)) {
            return true;
        } else {
            return false;
        }
    }

    public JFrame chooseFrame(String userDetails) {//choose the usertype and parameter
        JFrame frame = null;
        try {
            if (this.getUserMode().equals("Admin")) {
                return new AdminDashboardInterface();
            }
            if (this.getUserMode().equals("Medical Officer")) {
                return new MedicalOfficerDashboardInterface(userDetails);
            }
            if (this.getUserMode().equals("Patient")) {
                return new PatientDashboardInterface(userDetails);
            }
            if (this.getUserMode().equals("Receptionist")) {
                return new ReceptionistDashboardInterface(userDetails);
            }
        } catch (Exception e) {

        }
        return frame;
    }

    public String selectUserMode() {//select the user mode and find the file path
        try {
            if (this.getUserMode().equals("Admin")) {
                return this.getFilePath()[0];
            }
            if (this.getUserMode().equals("Medical Officer")) {
                return this.getFilePath()[1];
            }
            if (this.getUserMode().equals("Patient")) {
                return this.getFilePath()[2];
            }
            if (this.getUserMode().equals("Receptionist")) {
                return this.getFilePath()[3];
            }
        } catch (Exception e) {

        }
        return null;
    }

}
