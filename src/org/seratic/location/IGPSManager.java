package org.seratic.location;

import com.org.seratic.lucky.accessData.entities.TblPuntoGPS;
import com.org.seratic.lucky.gui.vo.PeticionGPS;

public interface IGPSManager {
public void posicionActualizada(PeticionGPS peticion,TblPuntoGPS puntoGPS);
}
