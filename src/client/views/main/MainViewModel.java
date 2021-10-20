package client.views.main;

import client.model.MessageModel;
import client.views.main.tools.TabList;
import com.sun.source.tree.IfTree;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import shared.transferobjects.Message;
import shared.transferobjects.User;
import shared.util.Subject;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Collections;
import java.util.List;

public class MainViewModel implements Subject {

    private PropertyChangeSupport support;

    private MessageModel messageModel;
    private ObservableList<User> users;


    public MainViewModel(MessageModel messageModel) {
        this.support = new PropertyChangeSupport(this);
        this.messageModel = messageModel;
        messageModel.addListener("USER_LIST_MODIFIED", this::onUserListModified);
        messageModel.addListener("NEW_MESSAGE", this::onNewMessage);
    }

    private void onNewMessage(PropertyChangeEvent event) {
        Message newMessage = (Message) event.getNewValue();
        if (newMessage.getReceiver() != null) {
            if (newMessage.getReceiver().equals(messageModel.getIdentity())) {
                support.firePropertyChange("CREATE_NEW_TAB",null, newMessage);
            }
        }

      //  if (!(tabList.existTab(newMessage.getReceiver().getID()))) {
       //     tabList.addTab(newMessage.getReceiver());
           // tabPane.getTabs().add(tabList.getTab(userSelected.getID()));
           // viewHandler.openTabView(tabList.getTab(userSelected.getID()), userSelected);
        } //else {
           // selectionModel.select(tabList.getTab(userSelected.getID()));
    //    }
    //}


    private void onUserListModified(PropertyChangeEvent event) {
        users.clear();
        for (User user : getSortedList()) {
            users.add(user);
        }
    }

    public void loadOnlineUsers() {
        this.users = FXCollections.observableArrayList(getSortedList());
    }

    public ObservableList<User> getUsers() {
        return users;
    }

    private List<User> getSortedList() {
        List<User> newList = messageModel.getUsers();
        Collections.sort(newList, (User u1, User u2) ->{
            return u1.getNickName().compareToIgnoreCase(u2.getNickName());
        });
        return newList;
    }

    public void userLeft() {
        messageModel.userLeft();
    }


    public User getIdentity() {
        return messageModel.getIdentity();
    }

    public Image getAvatar(){
        return messageModel.getAvatar();
    }

    @Override
    public void addListener(String evt, PropertyChangeListener listener) {
        support.addPropertyChangeListener(evt, listener);
    }

    @Override
    public void removeListener(String evt, PropertyChangeListener listener) {
        support.removePropertyChangeListener(evt, listener);

    }
}
