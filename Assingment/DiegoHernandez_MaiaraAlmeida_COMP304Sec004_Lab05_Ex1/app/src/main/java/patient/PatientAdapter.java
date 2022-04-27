package patient;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diegohernandez_maiaraalmeida_comp304sec004_lab05_ex1.R;

import java.util.ArrayList;
import java.util.List;

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.PatientHolder> {

    private List<Patient> patients = new ArrayList<>();

    private OnItemClickListener listener;

    @NonNull
    @Override
    public PatientHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.patient_row, parent, false);

        return new PatientHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientHolder holder, int position) {
        Patient currentPatient = patients.get(position);
        holder.textViewId.setText(String.valueOf(currentPatient.getId()));
        holder.textViewFirstname.setText(currentPatient.getFirstname());
        holder.textViewLastname.setText(currentPatient.getLastname());
        holder.textViewDepartment.setText(currentPatient.getDepartment());
        holder.textViewLNurseID.setText(String.valueOf(currentPatient.getNurseId()));
        holder.textViewRoom.setText(String.valueOf(currentPatient.getRoom()));

    }

    @Override
    public int getItemCount() {
        return patients.size();
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
        notifyDataSetChanged();
    }



    public Patient getPatientAt(int position) {
        return patients.get(position);
    }


    class PatientHolder extends RecyclerView.ViewHolder {
        private TextView textViewId;
        private TextView textViewFirstname;
        private TextView textViewLastname;
        private TextView textViewDepartment;
        private TextView textViewLNurseID;
        private TextView textViewRoom;

        public int getHolderPosition(){
            return getAdapterPosition();
        }

        public PatientHolder(@NonNull View itemView) {
            super(itemView);

            textViewId = itemView.findViewById(R.id.textViewPatientID);
            textViewFirstname = itemView.findViewById(R.id.textViewPatientFirstname);
            textViewLastname = itemView.findViewById(R.id.textViewPatientLastname);
            textViewDepartment = itemView.findViewById(R.id.textViewPatientDepartment);
            textViewLNurseID = itemView.findViewById(R.id.textViewPatientNurseID);
            textViewRoom = itemView.findViewById(R.id.textViewPatientRoom);


            //Implementing onClick listener
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(patients.get(position), getAdapterPosition());
                    }

                }
            });


        }


    }

    public interface OnItemClickListener {
        void onItemClick(Patient patient, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
