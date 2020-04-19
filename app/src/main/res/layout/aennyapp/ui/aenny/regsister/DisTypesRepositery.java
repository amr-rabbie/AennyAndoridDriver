package design.swira.aennyapp.ui.aenny.regsister;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;


import design.swira.aennyapp.data.room.aeeny.DisTypesIdDao;
import design.swira.aennyapp.data.room.aeeny.DisTypesIds;
import design.swira.aennyapp.data.room.aeeny.DataDataBase;
import retrofit2.Call;
import retrofit2.Callback;

public class DisTypesRepositery  {

    List<DisTypesIds> responseList;
    DisTypesIdDao githubsDao;
    LiveData<List<DisTypesIds>> allgithubs;

    public DisTypesRepositery(Application application) {
        DataDataBase gitHubsDatabase=DataDataBase.getInstance(application);
        githubsDao=gitHubsDatabase.disTypesIdDao();
        allgithubs=githubsDao.getAll();
    }



   /* public List<Response> getJakeWhartonGitHubsonline(String page, String perpage){
        GitHubsClient.getInstance().getGithubs(page,perpage).enqueue(new Callback<List<Response>>() {
            @Override
            public void onResponse(Call<List<Response>> call, retrofit2.Response<List<Response>> response) {
                responseList = response.body();
            }

            @Override
            public void onFailure(Call<List<Response>> call, Throwable t) {
                Log.e("err",t.getMessage());
            }
        });
        return responseList;
    }*/

    public void insert(DisTypesIds gitHubs){
        new insertAsy(githubsDao).execute(gitHubs);
    }

    public void update(DisTypesIds gitHubs){
        new updateAsy(githubsDao).execute(gitHubs);
    }

    public void delete(DisTypesIds gitHubs){
        new deleteAsy(githubsDao).execute(gitHubs);
    }

    public void deleteall(){
        new deleteallAsy(githubsDao).execute();
    }

    public LiveData<List<DisTypesIds>> getAllgithubs() {
        return allgithubs;
    }

    public static class deleteallAsy extends AsyncTask<Void,Void,Void>{
        private DisTypesIdDao githubsDao;

        public deleteallAsy(DisTypesIdDao githubsDao) {
            this.githubsDao = githubsDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            githubsDao.deleteAll();
            return null;
        }
    }

    public static class insertAsy extends AsyncTask<DisTypesIds,Void,Void>{
        private DisTypesIdDao githubsDao;

        public insertAsy(DisTypesIdDao githubsDao) {
            this.githubsDao = githubsDao;
        }

        @Override
        protected Void doInBackground(DisTypesIds... gitHubs) {
            githubsDao.insert(gitHubs[0]);
            return null;
        }
    }

    public static class updateAsy extends AsyncTask<DisTypesIds,Void,Void>{
        private DisTypesIdDao githubsDao;

        public updateAsy(DisTypesIdDao githubsDao) {
            this.githubsDao = githubsDao;
        }

        @Override
        protected Void doInBackground(DisTypesIds... gitHubs) {
            githubsDao.update(gitHubs[0]);
            return null;
        }
    }


    public static class deleteAsy extends AsyncTask<DisTypesIds,Void,Void>{
        private DisTypesIdDao githubsDao;

        public deleteAsy(DisTypesIdDao githubsDao) {
            this.githubsDao = githubsDao;
        }

        @Override
        protected Void doInBackground(DisTypesIds... gitHubs) {
            githubsDao.delete(gitHubs[0]);
            return null;
        }
    }

}
