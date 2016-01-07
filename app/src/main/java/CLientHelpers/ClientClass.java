package CLientHelpers;

/**
 * Created by mboyaa on 1/3/2016.
 */
public class ClientClass {

    public String C_ID = "";
    public String C_FN = "";
    public String C_LN = "";
    public String C_AGE = "";
    public String C_SEX = "";
    public String C_NRC = "";
    public String C_PHONE = "";

    public void ClientClass(){
        C_ID = "";
        C_FN = "";
        C_LN = "";
        C_AGE = "";
        C_SEX = "";
        C_NRC = "";
        C_PHONE = "";
    }

    public void setC_ID(String id){
        C_ID = id;
    }

    public void setC_FN(String fn){
        C_FN = fn;
    }

    public void setC_LN(String ln){
        C_LN = ln;
    }

    public void setC_AGE(String age){
        C_AGE = age;
    }

    public void setC_SEX(String sex){
        C_SEX = sex;
    }

    public void setC_NRC(String nrc){
        C_NRC = nrc;
    }

    public void setC_PHONE(String phone){
        C_PHONE = phone;
    }


    //Gets
    public String getC_FN(){
        return C_FN;
    }

    public String getC_LN(){
       return C_LN;
    }

    public String getC_AGE(){
        return C_AGE;
    }

    public String getC_SEX(){
        return C_SEX;
    }

    public String getC_NRC(){
        return C_NRC;
    }

    public String getC_PHONE(){
        return C_PHONE;
    }
}
