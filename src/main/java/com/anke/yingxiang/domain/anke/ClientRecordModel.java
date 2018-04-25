package com.anke.yingxiang.domain.anke;

import java.util.List;

public class ClientRecordModel {
    private ClientModel Client;
    private List<ClientModel> Clients;

    public ClientModel getClient() {
        return Client;
    }

    public void setClient(ClientModel client) {
        Client = client;
    }

    public List<ClientModel> getClients() {
        return Clients;
    }

    public void setClients(List<ClientModel> clients) {
        Clients = clients;
    }
}
