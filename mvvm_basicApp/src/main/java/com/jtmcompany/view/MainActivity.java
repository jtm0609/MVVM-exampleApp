package com.jtmcompany.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.jtmcompany.R;
import com.jtmcompany.model.UserProfile;
import com.jtmcompany.viewmodel.UserProfileViewModel;
import com.jtmcompany.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private UserProfileViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //뷰바인딩
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel=new ViewModelProvider(this).get(UserProfileViewModel.class);

        //룸과 라이브데이터를 한번 옵저버시켜놓으면 그 옵저버는 등록된 db를 계속 옵저빙한다.
        //뷰모델의 라이브데이터가 변경될때마다 콜백
        viewModel.userProfileList.observe(this, new Observer<List<UserProfile>>() {
            @Override
            public void onChanged(List<UserProfile> userProfileList) {
                Log.d("tag","onChanged");
                updateUserProfileList(userProfileList);
            }
        });
    }

    //라이브데이터의 데이터 출력
    private void updateUserProfileList(List<UserProfile> userProfileList){
        String userListText="사용자 목록";
        for(UserProfile userProfile : userProfileList){
            userListText+= "\n"+ userProfile.id+ ", "+ userProfile.name+ ", "+ userProfile.phone+", "+userProfile.address;
        }
        binding.userList.setText(userListText);
    }

    //버튼클릭
    //ROOM에 데이터 추가
    public void addUserProfile(View view){
        UserProfile userProfile=new UserProfile();
        userProfile.name=binding.name.getText().toString();
        userProfile.phone=binding.phone.getText().toString();
        userProfile.address=binding.address.getText().toString();
        viewModel.insert(userProfile);
    }
}
