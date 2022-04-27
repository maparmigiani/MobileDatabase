package test;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diegohernandez_maiaraalmeida_comp304sec004_lab05_ex1.R;

import java.util.ArrayList;
import java.util.List;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.TestHolder> {

    private List<Test> tests = new ArrayList<>();

    private OnItemClickListener listener;

    @NonNull
    @Override
    public TestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.test_row, parent, false);

        return new TestHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TestHolder holder, int position) {
        Test curentTest = tests.get(position);
        holder.textViewTestId.setText(String.valueOf(curentTest.getId()));
        holder.textViewPatientId.setText(String.valueOf(curentTest.getPatientId()));
        holder.textViewNurseId.setText(String.valueOf(curentTest.getNurseId()));
        holder.textViewBPL.setText(String.valueOf(curentTest.getBpl()));
        holder.textViewBPH.setText(String.valueOf(curentTest.getBph()));
        holder.textViewTemperature.setText(String.valueOf(curentTest.getTemperature()));



    }

    @Override
    public int getItemCount() {
        return tests.size();
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
        notifyDataSetChanged();
    }



    public Test getPatientAt(int position) {
        return tests.get(position);
    }


    class TestHolder extends RecyclerView.ViewHolder {
        private TextView textViewTestId;
        private TextView textViewPatientId;
        private TextView textViewNurseId;
        private TextView textViewBPL;
        private TextView textViewBPH;
        private TextView textViewTemperature;


        public TestHolder(@NonNull View itemView) {
            super(itemView);

            textViewTestId = itemView.findViewById(R.id.textViewTestId);
            textViewPatientId = itemView.findViewById(R.id.textViewTestPatientId);
            textViewNurseId = itemView.findViewById(R.id.textViewTestNurseId);
            textViewBPL = itemView.findViewById(R.id.textViewTestBPL);
            textViewBPH = itemView.findViewById(R.id.textViewTestBPH);
            textViewTemperature = itemView.findViewById(R.id.textViewTestTemperature);


            //Implementing onClick listener
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(tests.get(position), getAdapterPosition());
                    }

                }
            });


        }


    }

    public interface OnItemClickListener {
        void onItemClick(Test patient, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
