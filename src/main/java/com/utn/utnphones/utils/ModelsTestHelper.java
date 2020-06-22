package com.utn.utnphones.utils;

import com.utn.utnphones.models.*;
import com.utn.utnphones.models.enums.LineStatus;
import com.utn.utnphones.models.enums.UserType;
import lombok.Data;

@Data
public class ModelsTestHelper {
    Hash hash = new Hash();
    User user;
    Locality locality;
    Bill bill;
    PhoneLine phoneLine;
    Call call;
    Tariff tariff;
    Province province;
    LineType lineType;

    public ModelsTestHelper(){
        province =  new Province(1,"Buenos aires",null);
        locality = new Locality(1,this.province,"12 de octubre", "2317",null,null,null,null );
        user = new User(1,this.locality,"Guillermina","Latorre","guille", "42492167", this.hash.getHash("8050"), UserType.BACKOFFICE,true);
        tariff = new Tariff(1,this.locality, this.locality, 2.4, 3.35);
        lineType = new LineType(1,"RESIDENCIAL", 10);
        phoneLine = new PhoneLine(302, "2317678906",this.user, this.lineType, this.locality, LineStatus.ENABLED,true );
        phoneLine = new PhoneLine(302, "2317678906",this.user, this.lineType, this.locality, LineStatus.ENABLED,true );
    }


}
