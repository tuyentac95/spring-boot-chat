package com.codegym.service;

import com.codegym.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private static List<User> users;

    static {
        users = new ArrayList<>();
        users.add(new User("tuyen","12345","https://scontent.fhan2-4.fna.fbcdn.net/v/t1.0-9/118051414_2791844877808045_680768740254589378_n.jpg?_nc_cat=105&_nc_sid=09cbfe&_nc_ohc=CWKp-T2T3XYAX-NhMNy&_nc_ht=scontent.fhan2-4.fna&oh=56f7d44fdc210518f3cee98193f0855e&oe=5F9D8788"));
        users.add(new User("mai","12345","https://scontent.fhan2-4.fna.fbcdn.net/v/t1.0-9/120453842_389394409135898_6677555786255134881_o.jpg?_nc_cat=110&_nc_sid=09cbfe&_nc_ohc=7CVB0kfRRJ4AX_l3NT-&_nc_ht=scontent.fhan2-4.fna&oh=3612bc12686d49f23a596af57e806869&oe=5F9EE88C"));
        users.add(new User("linh","12345","https://i.pinimg.com/originals/a3/c3/72/a3c372cb86479ed6155a7ee6dd20b487.jpg"));
        users.add(new User("ngoc","12345","https://thuthuatnhanh.com/wp-content/uploads/2020/01/girl-xinh.jpg"));
        users.add(new User("hoa","12345","https://mcnewsmd1.keeng.net/netnews/archive/images/2020041117/tinngan_051138_244543106_0wap_320.jpg"));
        users.add(new User("nhung","12345","https://znews-photo.zadn.vn/w660/Uploaded/ofh_btgazspf/2019_10_25/66643097_161307101670951_7404188023044511528_n_1.jpg"));
    }

    public List<User> userList(){
        return users;
    }

    public List<User> chattingList(String username){
        List<User> chattingList = new ArrayList<>();
        List<User> userList = userList();
        for(User user : userList){
            if(user.getUsername().equals(username)) continue;
            chattingList.add(user);
        }
        return chattingList;
    }
}
