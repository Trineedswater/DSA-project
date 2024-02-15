/*
 * Abstract data type used to represent a weighted edge in a graph
 */

public class GraphEdge
{
    private double distance;
    private String from;
    private String to;
    private String label;

    public GraphEdge(String pFrom, String pTo, String pLab, double pDis)
    {
        distance = pDis;
        from = pFrom;
        to = pTo;
        label = pLab;
    }

    public String getLabel()
    {
        return label;
    }
    public void setLabel(String pLab)
    {
        label = pLab;
    }
    public String getFrom()
    {
        return from;
    }
    public void setFrom(String pFrom)
    {
        from = pFrom;
    }
    public String getTo()
    {
        return to;
    }
    public void setTo(String pTo)
    {
        to = pTo;
    }
    public double getDis()
    {
        return distance;
    }
    public void setDis(double pDis)
    {
        distance = pDis;
    }

    public String display()
    {
        StringBuilder builder = new StringBuilder("[");
        builder.append(label);
        builder.append(",");
        builder.append(from);
        builder.append(",");
        builder.append(to);
        builder.append(",");
        builder.append(distance);
        builder.append("]");
        String ret = builder.toString();
        return ret;
    }
}