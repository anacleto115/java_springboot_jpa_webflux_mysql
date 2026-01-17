
package lib_domain.Core;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Convertions 
{
    public static String DateToString(Date date)
    {
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        return formatter.format(new Date());
    }
}
