import java.util.*;
//THIS FILE CONTAINS ALL CLASSES ALONG WITH THE MAIN CLASS.....

class ATM {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        AtmInstructions.Instrcutions();
        withdrawInst ccc= new withdrawInst();
        impIns aaa= new impIns();
        aaa.genInstruc();
        String adminPassword="admin123";

        //AS WE KNOW BANK ONLY GIVES US THE BANK ACCOUNT NUMBER, SO ACCORDING TO THE QUESTION I GAVE A RANDOM 5 DIGIT BANK ACCOUNT NUMBER
        //AS WE ARE CREATING BANK ACCOUNTS FOR MULTIPLE USERS I JUST GAVE THE 2ND,3RD.. BANK USERS DIFFERENT ACC. NO. BY JUST ADDING +5
        //TO THE OLDER ACCOUNT NUMBER
        int input2 = 10000;
        AtmScreen abc= new AtmScreen();

        //I CALLED THE HELLO METHOD FROM THE ATMSCREEN CLASS WHICH I IMPLEMENTED FROM THE INTERFACE OF CENTRAL ATM 
        abc.DisplayHello();
        bankScreen xyz = new bankScreen();

        //INSTEAD OF PREDEFINING THE USERS BANK DETAILS, FIRSTLY MY CODE WILL CREATE THE REQUIRED NUMBER OF  BANK ACCOUNTS BY TAKING USER DETAILS
        // AND THEN THE USERS CAN USE THE ATM BY GIVING THEIR ACCOUNT NUMBER AND ATM PIN
        System.out.println("DEAR ADMIN , ENTER HOW MANY BANK ACCOUNTS TO CREATE");
        int ba=scan.nextInt();

        //SO FOR THE MULTIPLE USERS THING I CREATED A PERSONS OBJECT ARRAY,AS WE CAN SEE BELOW...
        CreateBankAccount[] persons = new CreateBankAccount[ba];
        while (true) {
            // AFTER CREATING THE REQUIRED NUMBER OF BANK ACCOUNTS THE USER SHOULD ENTER THE EXISTING USER OPTION BY ITS NUMBER(2 HERE)
            
            System.out.println("1) ADMIN  \n2)EXISTING USER");
            System.out.println("ENTER '2' IF YOU HAVE A BANK ACCOUNT AND WANTED TO USE ATM");
            int k = scan.nextInt();

            //THE FIRST OPTION (NEW USER...) TAKES ALL THE INPUTS FROM THE USER
            if (k == 1) {
                System.out.println("PLEASE ENTER YOUR PASSWORD");
                String ap=scan.next();
                if(ap.equals(adminPassword)){
                    
                
                

                int kk = ba;
                int i = 0;
                

                while (kk != 0) {
                   xyz.DisplayHello();
                   
                    System.out.println(" \n Enter  Name of the user");
                    String input1 = scan.next();
                    
                    
                    System.out.println("The 5 digit Account Number is "+input2);

                    System.out.println("SET ATM PIN");
                    int input3 = scan.nextInt();
                    System.out.println("Please select the type of ACCOUNT");
                    System.out.println("1)SAVINGS ACCOUNT \n2)CURRENT ACCOUNT");
                    int input4 = scan.nextInt();
                    System.out.println("Please enter  Address of the user");
                    String input6 = scan.next();
                    System.out.println("Please enter PanCardNumber of the user");
                    String input7=scan.next();
                    System.out.println("Please enter AdharNumber of the user");
                    String input8=scan.next();
                    System.out.println("Please enter Age of the user");
                    int input9=scan.nextInt();
                    System.out.println("ACCOUNT SUCCESSFULLY CREATED");
                    double input5=0;
                    if(input4==1){
                        System.out.println("PLEASE DEPOSIT THE AMOUNT OF MINIMUM 10,000 AS YOURS IS SAVINGS ACCOUNT");
                         input5 = scan.nextDouble();
                    }
                    else if(input4==2){
                        System.out.println("PLEASE DEPOSIT SOME CASH");
                         input5 = scan.nextDouble();
                    }

                    
                    
                    //I DECLARED THE RESPECTIVE PERSONS INPUT  DETAILS IN THE RESPECTIVE PERSON[i] AS WE CAN SEE BELOW...
                    persons[i] = new CreateBankAccount(input2, input3, input1, input4,input5,input6,input7,input8,input9);
                    input2+=1;
                    System.out.println("");
                    i++;
                    kk--;
                }
            }
            else{
                System.out.println("Password is wrong");
                System.exit(0);
            }
            } 
            
            else if (k == 2) {
                // THIS IS FOR THE ATM PART, FIRSTLY IT TAKES ACCOUNT NUMBER AND MATCHES WITH THE RESPECTIVE PERSON[i] WHICH IT MATCHES,
                //AND TAKES THE INPUT OF ATM PIN AND IF THE ATM PIN MATCHES THE RESPECTIVE PERSONS ACCOUNT NUMBER,THEN IT CONTINUES..
                // HERE THE USER HAS ONLY 3 ATTEMPTS TO ENTER THE CORRECT PIN, ELSE THE BANK ACCOUNT WILL BE TEMPORARILY BLOCKED
                System.out.println(" Enter your ACCOUNT NUMBER");
                int ac = scan.nextInt();
                int gotIt = 0;
                for (int t = 0; t < ba; t++) {
                    if (persons[t].GetAccountNumber() == ac) {
                        gotIt = t;
                    }
                }
                System.out.println("ENTER YOUR ATM PIN");
                int atmpinn = scan.nextInt();
                int cnnnt=2;
                
                
                if (persons[gotIt].GetAtmPin() != atmpinn) {
                    
                    System.out.println("INVALID ATM PIN");
                    cnnnt--;
                    System.out.println(cnnnt+"attempt remaining");
                   

                    

                } 
                System.out.println("PLEASE RE-ENTER YOUR ATM PIN");
                
                atmpinn = scan.nextInt();
                
                if (persons[gotIt].GetAtmPin() != atmpinn) {
                    
                    System.out.println("INVALID ATM PIN");
                    
                    System.out.println("YOUR ACCOUNT IS TEMPORARILY BLOCKED, PLEASE CONTACT BANK FOR RESETTING YOUR ATM PIN");
                    break;
                   

                    

                } 

                else {
                
                    double blnce = persons[gotIt].getDepositedCash();
                    
                    System.out.println("Please select Type of account \n 1)SAVINGS ACCOUNT \n 2)CURRENT ACCOUNT");
                    int type = scan.nextInt();
                    if ((type == 1) && (persons[gotIt].getAccountType()==1)) {
                        // HERE THE ATM SCREEN DISPLAYS 5 OPTIONS OF 1)WITHDRAW CASH  2)DEPOSIT CASH  3)BALANCE  4)UPDATE DETAILS (INCLUDES RESET PIN)  5)VIEW DETAILS"
                        //THE USER SHOULD ENTER THE CORRESPONDING NUMBER RESPECTIVELY
                        System.out.println("1)WITHDRAW CASH \n 2)DEPOSIT CASH \n 3)BALANCE \n 4)UPDATE DETAILS (INCLUDES RESET PIN) \n 5)VIEW DETAILS");
                        int g = scan.nextInt();
                        if (g == 1) {
                            ccc.genInstruc();
                            System.out.println("ENTER AMOUNT");
                            int amt = scan.nextInt();
                            if (blnce - amt < 0) {
                                // IF USER WITHDRAWS CASH MORE THAN THE AMOUNT WHAT IS IN THE BANK ACCOUNT, THE ATM SCREEN DISPLAYS 'INSUFFICIENT BALANCE'
                                System.out.println("INSUFFICIENT BALANCE");
                            }
                            if(amt>10000) {
                                //AS WE KNOW IN THE ATMS(MAINLY IN SBI) THE MAXIMUM AMOUNT THAT CAN BE DRAWN AT A TIME IS 10,000, SO IF WITHDRAW AMT>10,000, THE ATM DISPLAYS THE FOLLOWING MESSAGE
                                System.out.println("TRANSACTION IS DECLINED AS TRANSACTION LIMIT OF 10,000 IS EXCEEDED!!!");
                            }
                            
                            
                            else {
                                
                                persons[gotIt].setDepositCash(blnce - amt);
                                System.out.println("CASH IS BEGING DISPENSED....");
                                System.out.println("PLEASE COLLECT THE CASH \n DO U WANT RECEIPT? \n 1)YES \n 2)NO");
                                int receipt = scan.nextInt();
                                // THIS PORTAL IS FOR THE PRINTING OF THE RECEIPT IF THE USER WANTS
                                if (receipt == 1) {
                                    System.out.println("DATE : 30/9/2022");
                                    System.out.println("TIME : 10:00 A.M.");
                                    System.out.println("NAME : " + persons[gotIt].getUserName());
                                    System.out.println("A/C NUMBER : " + persons[gotIt].GetAccountNumber());
                                    System.out.println("AMOUNT WITHDRAWN : " + amt);
                                    if(type==1){
                                        System.out.println("SAVINGS ACCOUNT");
                                    }
                                    else if(type==2){
                                        System.out.println("CURRENT ACCOUNT");
                                    }
                                    abc.DisplayTq();

                                } else {
                                    System.out.println("THANK YOU!!!");
                                }

                            }

                        }
                        else if(g==2){
                            //THIS PORTAL IS FOR THE USER DEPOSITING THE CASH, THE ATM CAN ACCEPT 2RS,5RS,100RS,500RS,2000RS AS PER THE QUESTION GIVEN
                            System.out.println("WE ARE ACCEPTING ONLY \n 2 rs.coin \n 5 rs.coin \n 100 rs. notes \n 500 rs. notes \n 2000 rs. notes");
                            System.out.println("please enter the Number of 2 rs. coins");
                            int twors=scan.nextInt();
                            System.out.println("please enter the Number of 5 rs. coins");
                            int fivers=scan.nextInt();
                            System.out.println("please enter the Number of 100 rs. notes");
                            int hunrs=scan.nextInt();
                            System.out.println("please enter the Number of  500 rs. notes");
                            int fivhu=scan.nextInt();
                            System.out.println("please enter the Number of 2000 rs. notes");
                            int twoth=scan.nextInt();
                            // THE ATM FINALLY CALCULATES THE TOTAL AMOUNT DEPOSITED AND INCREASES THE ACCOUNT BALANCE BY THE SAME
                            double ammmt=(twors*2)+(fivers*5)+(hunrs*100)+(fivhu*500)+(twoth*2000);
                            persons[gotIt].setDepositCash(ammmt+blnce);
                            System.out.println("YOUR cash is successfully deposited");
                            abc.DisplayTq();

                        }
                        else if(g==3){
                            //THIS PORTAL IS FOR DISPLAYING THE BALACE OF THE USER 
                            System.out.println("YOUR ACCOUNT BALANCE IS "+persons[gotIt].getDepositedCash());
                        }
                        else if(g==4){
                            // THIS PORTAL IS FOR THE DETAILS TO BE UPDATED INCLUDING THE RESETTING OF THE ATM PIN
                            System.out.println("WHICH OF THE FOLLOWING DETAILS YOU WANT TO UPDATE? \n 1)ADDRESS \n 2)USERNAME \n 3)CHANGE ATM PIN");
                            int gg=scan.nextInt();
                            if(gg==1){
                                System.out.println("PLEASE ENTER THE ADDRESS TO BE UPDATED");
                                String newA=scan.next();
                                persons[gotIt].setAddress(newA);
                                System.out.println("YOUR ADDRESS IS SUCCESFULLY UPDATED!!!");;

                            }
                            if(gg==2){
                                System.out.println("PLEASE ENTER THE NAME TO BE UPDATED");
                                String newN=scan.next();
                                persons[gotIt].setName(newN);
                                System.out.println("YOUR NAME IS SUCCESFULLY UPDATED");
                            }
                            if(gg==3){
                                System.out.println("PLEASE ENTER THE NEW PIN THAT TO BE SET");
                                int newP=scan.nextInt();
                                persons[gotIt].SetPin(newP);
                                System.out.println("YOUR ATM PIN IS SUCCESFULLY UPDATED!!!");

                            }
                            
                        }
                        else if(g==5){
                            //  THIS PORTAL IS FOR THE USER DETAILS DISPLAYING(AN EXTRA FEATURE I ADDED)
                            System.out.println("NAME :"+persons[gotIt].getUserName());
                            System.out.println("AGE :"+persons[gotIt].GetAge());
                            System.out.println("ACCOUNT_NUMBER :"+persons[gotIt].GetAccountNumber());
                            System.out.println("PAN_CARD_NUMBER :"+persons[gotIt].getPanNumber());
                            System.out.println("ADHAR_CARD_NUMBER :"+persons[gotIt].GetAdharNumber());
                            

                        }
                    }
                   
                    else if((type==2) && (persons[gotIt].getAccountType()==2)){
                                                // HERE THE ATM SCREEN DISPLAYS 5 OPTIONS OF 1)WITHDRAW CASH  2)DEPOSIT CASH  3)BALANCE  4)UPDATE DETAILS (INCLUDES RESET PIN)  5)VIEW DETAILS"
                        //THE USER SHOULD ENTER THE CORRESPONDING NUMBER RESPECTIVELY
                        System.out.println("1)WITHDRAW CASH \n 2)DEPOSIT CASH \n 3)BALANCE \n 4)UPDATE DETAILS (INCLUDES RESET PIN) \n 5)VIEW DETAILS");
                        int g = scan.nextInt();
                        if (g == 1) {
                            ccc.genInstruc();
                            System.out.println("ENTER AMOUNT");
                            int amt = scan.nextInt();
                            if (blnce - amt < 0) {
                                // IF USER WITHDRAWS CASH MORE THAN THE AMOUNT WHAT IS IN THE BANK ACCOUNT, THE ATM SCREEN DISPLAYS 'INSUFFICIENT BALANCE'
                                System.out.println("INSUFFICIENT BALANCE");
                            }
                            if(amt>10000) {
                                //AS WE KNOW IN THE ATMS(MAINLY IN SBI) THE MAXIMUM AMOUNT THAT CAN BE DRAWN AT A TIME IS 10,000, SO IF WITHDRAW AMT>10,000, THE ATM DISPLAYS THE FOLLOWING MESSAGE
                                System.out.println("TRANSACTION IS DECLINED AS TRANSACTION LIMIT OF 10,000 IS EXCEEDED!!!");
                            }
                            
                            
                            else {
                                
                                persons[gotIt].setDepositCash(blnce - amt);
                                System.out.println("CASH IS BEGING DISPENSED....");
                                System.out.println("PLEASE COLLECT THE CASH \n DO U WANT RECEIPT? \n 1)YES \n 2)NO");
                                int receipt = scan.nextInt();
                                // THIS PORTAL IS FOR THE PRINTING OF THE RECEIPT IF THE USER WANTS
                                if (receipt == 1) {
                                    System.out.println("DATE : 30/9/2022");
                                    System.out.println("TIME : 10:00 A.M.");
                                    System.out.println("NAME : " + persons[gotIt].getUserName());
                                    System.out.println("A/C NUMBER : " + persons[gotIt].GetAccountNumber());
                                    System.out.println("AMOUNT WITHDRAWN : " + amt);
                                    if(type==1){
                                        System.out.println("SAVINGS ACCOUNT");
                                    }
                                    else if(type==2){
                                        System.out.println("CURRENT ACCOUNT");
                                    }
                                    abc.DisplayTq();

                                } else {
                                    System.out.println("THANK YOU!!!");
                                }

                            }

                        }
                        else if(g==2){
                            //THIS PORTAL IS FOR THE USER DEPOSITING THE CASH, THE ATM CAN ACCEPT 2RS,5RS,100RS,500RS,2000RS AS PER THE QUESTION GIVEN
                            System.out.println("WE ARE ACCEPTION ONLY \n 2 rs.coin \n 5 rs.coin \n 100 rs. notes \n 500 rs. notes \n 2000 rs. notes");
                            System.out.println("please enter the Number of 2 rs. coins");
                            int twors=scan.nextInt();
                            System.out.println("please enter the Number of 5 rs. coins");
                            int fivers=scan.nextInt();
                            System.out.println("please enter the Number of 100 rs. notes");
                            int hunrs=scan.nextInt();
                            System.out.println("please enter the Number of  500 rs. notes");
                            int fivhu=scan.nextInt();
                            System.out.println("please enter the Number of 2000 rs. notes");
                            int twoth=scan.nextInt();
                            // THE ATM FINALLY CALCULATES THE TOTAL AMOUNT DEPOSITED AND INCREASES THE ACCOUNT BALANCE BY THE SAME
                            double ammmt=(twors*2)+(fivers*5)+(hunrs*100)+(fivhu*500)+(twoth*2000);
                            persons[gotIt].setDepositCash(ammmt+blnce);
                            System.out.println("YOUR cash is successfully deposited");
                            abc.DisplayTq();

                        }
                        else if(g==3){
                            //THIS PORTAL IS FOR DISPLAYING THE BALACE OF THE USER 
                            System.out.println("YOUR ACCOUNT BALANCE IS "+persons[gotIt].getDepositedCash());
                        }
                        else if(g==4){
                            // THIS PORTAL IS FOR THE DETAILS TO BE UPDATED INCLUDING THE RESETTING OF THE ATM PIN
                            System.out.println("WHICH OF THE FOLLOWING DETAILS YOU WANT TO UPDATE? \n 1)ADDRESS \n 2)USERNAME \n 3)CHANGE ATM PIN");
                            int gg=scan.nextInt();
                            if(gg==1){
                                System.out.println("PLEASE ENTER THE ADDRESS TO BE UPDATED");
                                String newA=scan.next();
                                persons[gotIt].setAddress(newA);
                                System.out.println("YOUR ADDRESS IS SUCCESFULLY UPDATED!!!");;

                            }
                            if(gg==2){
                                System.out.println("PLEASE ENTER THE NAME TO BE UPDATED");
                                String newN=scan.next();
                                persons[gotIt].setName(newN);
                                System.out.println("YOUR NAME IS SUCCESFULLY UPDATED");
                            }
                            if(gg==3){
                                System.out.println("PLEASE ENTER THE NEW PIN THAT TO BE SET");
                                int newP=scan.nextInt();
                                persons[gotIt].SetPin(newP);
                                System.out.println("YOUR ATM PIN IS SUCCESFULLY UPDATED!!!");

                            }
                            
                        }
                        else if(g==5){
                            //  THIS PORTAL IS FOR THE USER DETAILS DISPLAYING(AN EXTRA FEATURE I ADDED)
                            System.out.println("NAME :"+persons[gotIt].getUserName());
                            System.out.println("AGE :"+persons[gotIt].GetAge());
                            System.out.println("ACCOUNT_NUMBER :"+persons[gotIt].GetAccountNumber());
                            System.out.println("PAN_CARD_NUMBER :"+persons[gotIt].getPanNumber());
                            System.out.println("ADHAR_CARD_NUMBER :"+persons[gotIt].GetAdharNumber());
                            

                        }

                    }
                    else{
                        System.out.println("No  account exists with the given details");
                    }
                }
            } 
            else {
                break;
            }
            continue;
        }
    }

}


// THIS INTERFACE I CREATED IS MAINLY FOR DISPLAYING THE MESSAGES TO THE USER, WE KNOW INTERFACE IS USED FOR 100% ABSTRACTION
interface centralATM  {
    public void DisplayHello();
    public void DisplayTq();

}

class AtmScreen implements centralATM{
    @Override
    public void DisplayHello(){
        System.out.print("HELLO!! \nWELCOME \n");
    }
    @Override
    public void DisplayTq(){
        System.out.println("THANK YOU FOR BANKING WITH US!! \n ");
    }
}
class bankScreen implements centralATM{
    @Override
    public void DisplayHello(){
        System.out.print("HELLO!! \n WELCOME  please create the bank account BY entering the following details");
    }
    @Override
    public void DisplayTq(){
        System.out.println("THANK YOU  \n ");
    }

}

  abstract class AtmInstructions{
    public abstract void genInstruc();
    static void Instrcutions(){
        System.out.println("THE ATM IS UNDER CC T.V SURVEILLANCE");
    }

 }
 class impIns extends AtmInstructions{
    @Override
    public void genInstruc(){
        System.out.println("PLEASE DO NOT ENTER ATM WHEN ITS BEING USED BY OTHER PERSON");
    }
 }
 class withdrawInst extends AtmInstructions{
    @Override
    public void genInstruc(){
        System.out.println("you can  withdraw maximum amount of 10,000 At a time");
    }
 }


// THE MAIN CLASS OF CREATINGBANKACCOUNT IS BELOW,IT HAS THE FIELDS OF AccountNumber, AtmPin,Age,UserName, AccountType,ADDRESS,PanCardNumber,AdharNumber,DepositCash...
//THIS INCLUDES THE GETTERS SETTERS etc...


class CreateBankAccount {
    //access modifiers
    private int AccountNumber, AtmPin,Age, AccountType;
    private String UserName,ADDRESS,PanCardNumber,AdharNumber;
    private double DepositCash;

    CreateBankAccount(int AccountNumber, int AtmPin, String UserName, int AccountType, double DepositCash,String ADDRESS,String PanCardNumber,String AdharNumber,int Age ) {
        this.AccountNumber = AccountNumber;
        this.AtmPin = AtmPin;
        this.UserName = UserName;
        this.AccountType = AccountType;
        this.DepositCash = DepositCash;
        this.AdharNumber=AdharNumber;
        this.ADDRESS=ADDRESS;
        this.PanCardNumber=PanCardNumber;
        this.Age=Age;
    }
    public void setDepositCash(double Depositcash){
        this.DepositCash=Depositcash;
    }
    public void setAddress(String ADDRESS){
        this.ADDRESS=ADDRESS;
    }
    public void setName(String UserName){
        this.UserName=UserName;
    }
    public void SetPin(int AtmPin){
        this.AtmPin=AtmPin;
    }
    public int GetAccountNumber() {
        return AccountNumber;
    }

    public int GetAtmPin() {
        return AtmPin;
    }

    public String getUserName() {
        return UserName;
    }

    public int getAccountType() {
        return AccountType;
    }

    public double getDepositedCash() {
        return DepositCash;
    }
    public String getAddress(){
        return ADDRESS;
    }
    public String getPanNumber(){
        return PanCardNumber;
    }
    public String GetAdharNumber(){
        return AdharNumber;
    }
    public int GetAge(){
        return Age;
    }




}

