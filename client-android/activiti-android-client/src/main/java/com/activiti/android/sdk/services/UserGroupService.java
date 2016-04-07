/*
 *  Copyright (C) 2005-2016 Alfresco Software Limited.
 *
 *  This file is part of Alfresco Activiti Client.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.activiti.android.sdk.services;

import okhttp3.ResponseBody;
import retrofit2.Callback;

import com.activiti.client.api.AdminAPI;
import com.activiti.client.api.UserGroupAPI;
import com.activiti.client.api.model.common.ResultList;
import com.activiti.client.api.model.idm.LightGroupRepresentation;
import com.activiti.client.api.model.idm.LightUserRepresentation;
import com.alfresco.client.RestClient;

/**
 * Created by jpascal on 11/12/2014.
 */
public class UserGroupService extends ActivitiService
{
    protected UserGroupAPI api;

    protected AdminAPI adminAPI;

    UserGroupService(RestClient client)
    {
        super(client);
        api = client.retrofit.create(UserGroupAPI.class);

    }

    // ///////////////////////////////////////////////////////////////////
    // LIST
    // ///////////////////////////////////////////////////////////////////
    public String getPicture(Long userId)
    {
        return String.format(restClient.endpoint.concat("/api/enterprise/users/%s/picture"), userId);
    }

    public void getUsers(String filter, Callback<ResultList<LightUserRepresentation>> callback)
    {
        api.getUsers(filter, null, null, null, null, null, null).enqueue(callback);
    }

    public void getUsers(String filter, String excludeTaskId, String excludeProcessId, String groupId, String tenantId,
            Callback<ResultList<LightUserRepresentation>> callback)
    {
        api.getUsers(filter, null, null, excludeTaskId, excludeProcessId, groupId, tenantId).enqueue(callback);
    }

    public void getGroups(String filter, String groupId, Callback<ResultList<LightGroupRepresentation>> callback)
    {
        api.getGroups(filter, groupId).enqueue(callback);
    }

    public void getUsersForGroup(String groupId, Callback<ResultList<LightUserRepresentation>> callback)
    {
        api.getUsersForGroup(groupId).enqueue(callback);
    }

    public void isAdmin(Callback<ResponseBody> callback)
    {
        if (adminAPI == null)
        {
            adminAPI = restClient.retrofit.create(AdminAPI.class);
        }
        adminAPI.isAdmin().enqueue(callback);
    }

}
