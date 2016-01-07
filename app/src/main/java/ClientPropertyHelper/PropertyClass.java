package ClientPropertyHelper;

/**
 * Created by mboyaa on 1/4/2016.
 */
public class PropertyClass {

    public String PropertyID;
    public String PropertyType;
    public String PropertyOwnerNrc;
    public String PropertyDescription;
    public String PropertyOwnerID;

    public void PropertyClass(){
        PropertyID = "";
        PropertyType = "";
        PropertyOwnerNrc = "";
        PropertyDescription = "";
        PropertyOwnerID = "";
    }


    public String getPropertyID(){
        return PropertyID;
    }

    public String getPropertyType(){
        return PropertyType;
    }

    public String getPropertyOwnerNrc(){
        return PropertyOwnerNrc;
    }

    public String getPropertyDescription(){
        return PropertyDescription;
    }

    public String getPropertyOwnerID(){
        return PropertyOwnerID;
    }
}
