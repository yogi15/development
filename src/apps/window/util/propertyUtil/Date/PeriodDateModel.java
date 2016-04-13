package apps.window.util.propertyUtil.Date;
 
import apps.window.util.propertyUtil.Date.Provider.ProviderReferenceDate;
import apps.window.util.propertyUtil.Date.Provider.ProviderTimeZone;

import com.jidesoft.combobox.DefaultDateModel;

public class PeriodDateModel extends DefaultDateModel {
    public PeriodDateModel(final ProviderTimeZone timeZoneProvider) {
        super();
        init(null, new DefaultPeriodCalculator(), timeZoneProvider);
    }

    public PeriodDateModel(ProviderReferenceDate referenceDateProvider, final ProviderTimeZone timeZoneProvider) {
        super();
        init(referenceDateProvider, new DefaultPeriodCalculator(), timeZoneProvider);
    }

    public PeriodDateModel(ProviderReferenceDate referenceDateProvider, PeriodAdapter tenorCalculator, final ProviderTimeZone timeZoneProvider) {
        super();
        init(referenceDateProvider, tenorCalculator, timeZoneProvider);
    }

    private void init(final ProviderReferenceDate referenceDateProvider, final PeriodAdapter tenorCalculator, final ProviderTimeZone timeZoneProvider) {
        setDateFormat(new PeriodDateFormat(referenceDateProvider, tenorCalculator, timeZoneProvider));
    }

}