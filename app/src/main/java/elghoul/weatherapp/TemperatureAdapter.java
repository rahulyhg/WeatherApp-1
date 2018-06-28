package elghoul.weatherapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class TemperatureAdapter extends RecyclerView.Adapter<TemperatureAdapter.TemperatureViewHolder> {
private Context context;
CityTemperatureData tempData;

    public TemperatureAdapter(Context context,CityTemperatureData tempData) {
        this.context=context;
        this.tempData=tempData;
    }

    @Override
    public TemperatureViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from( context );
       View view=inflater.inflate( R.layout.city_temperature,parent,false );
        return new TemperatureViewHolder( view );
    }

    @Override
    public void onBindViewHolder(TemperatureViewHolder holder, int position) {
        holder.txtLocation.setText( tempData.getLocation().getRegion()+","+tempData.getLocation().getCountry());
        holder.txtDate.setText( tempData.getForecast().getForecastday().get( position ).getDate() );
        holder.txtCondition.setText( tempData.getForecast().getForecastday().get( position ).getDay().getCondition().getText() );

        holder.txtAstro.setText(tempData.getForecast().getForecastday().get( position ).getAstro().getSunrise() +'\n'+
                                tempData.getForecast().getForecastday().get( position ).getAstro().getSunset()+'\n'+
                                tempData.getForecast().getForecastday().get( position ).getAstro().getMoonrise()+'\n'+
                                tempData.getForecast().getForecastday().get( position ).getAstro().getMoonset());

        Picasso.with( context ).load( tempData.getForecast().getForecastday().get( position ).getDay().getCondition().getIcon() ).into( holder.imgTempState );

            String[] forecast=new String[]{"max temp : "+tempData.getForecast().getForecastday().get( position ).getDay().getMaxtemp_c()+" °C"
                    ,"min temp : "+tempData.getForecast().getForecastday().get( position ).getDay().getMintemp_c()+" °C"
                    ,"max wind :"+tempData.getForecast().getForecastday().get( position ).getDay().getMaxwind_kph()+" kph"
                    ,"Avg humidity : "+ tempData.getForecast().getForecastday().get( position ).getDay().getAvghumidity()
                    };
        ListAdapter adapter=new ArrayAdapter(context,android.R.layout.simple_list_item_1,forecast) ;

        holder.DataList.setAdapter( adapter );

    }

    @Override
    public int getItemCount() {
        return this.tempData.getForecast().getForecastday().size();
    }



    class TemperatureViewHolder extends RecyclerView.ViewHolder{

        ImageView imgTempState;
        ListView DataList;
        TextView txtCondition,txtLocation,txtDate,txtAstro;
    public TemperatureViewHolder(View itemView) {
        super( itemView );
        imgTempState=itemView.findViewById( R.id.imgTemp );
        DataList=itemView.findViewById( R.id.DataFieldsList );

        txtAstro=itemView.findViewById( R.id.txtAstro );
        txtCondition=itemView.findViewById( R.id.txtCondition );
        txtDate=itemView.findViewById( R.id.txtDate );
        txtLocation=itemView.findViewById( R.id.txtLocation );

    }
}
}