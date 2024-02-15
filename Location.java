/*
* class to hold the fire risk data of a location
*/

public class Location
{
    private String name;
    private int temp;
    private int hum;
    private int wind;
    private int risk;

    public Location(String pName, int pTemp, int pHum, int pWind)
    {
        name = pName;
        temp = pTemp;
        hum = pHum;
        wind = pWind;
        risk = calcRisk(pTemp, pHum, pWind);
    }

    public Location(String pName)
    {
        name = pName;
        temp = 23;
        hum = 40;
        wind = 44;
        risk = calcRisk(temp, hum, wind);

    }

    private int calcRisk(int pTemp, int pHum, int pWind)
    {
        int t, h, w, r;

        // converting all parameters to the same scale from 1-100

        if (pTemp < 32) // low risk
        {
            t = pTemp / 2;
        }
        else // high risk
        {
            t = (pTemp - 32) * 5 + 16;
        }

        if (pHum > 50) // low risk
        {
            h = (pHum - 100) * -6 / 25;
        }
        else if (pHum < 30) // high risk
        {
            h = (pHum - 30) * -16 / 30 + 84;
        }
        else
        {
            h = (pHum - 50) * -17 / 5 + 16;
        }

        if (pWind < 40) // low risk
        {
            w = pWind * 2 / 5;
        }
        if (pWind > 55) // high risk
        {
            w = (pWind - 55) * 8 / 45 + 84;
        }
        else
        {
            w = (pWind - 40) * 68 / 15 + 16;
        }

        // returns the average
        r = (t + h + w)/3;
        return r;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String pName)
    {
        name = pName;
    }

    public int getTemp()
    {
        return temp;
    }

    public void setTemp(int pTemp)
    {
        temp = pTemp;
        risk = calcRisk(pTemp, hum, wind);
    }

    public int getHum()
    {
        return hum;
    }

    public void setHum(int pHum)
    {
        hum = pHum;
        risk = calcRisk(temp, pHum, wind);
    }

    public int getWind()
    {
        return wind;
    }

    public void setWind(int pWind)
    {
        wind = pWind;
        risk = calcRisk(temp, hum, pWind);
    }

    public int getRisk()
    {
        return risk;
    }

    public String display()
    {
        StringBuilder build = new StringBuilder("[");
        build.append(name);
        build.append(",");
        build.append(temp);
        build.append(",");
        build.append(hum);
        build.append(",");
        build.append(wind);
        build.append(",");
        build.append(risk);
        build.append("]");
        String ret = build.toString();
        return ret;
    }
}