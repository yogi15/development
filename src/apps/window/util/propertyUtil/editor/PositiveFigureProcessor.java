package apps.window.util.propertyUtil.editor;
 
public class PositiveFigureProcessor implements PropertyValueProcessor<Double> {
    public Double process(Double number) {
        if (number == null)
            return null;
        else if (number < 0)
            return -number;
        else
            return number;
    }
}
