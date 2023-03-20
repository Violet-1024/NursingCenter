package com.dryyzx.backend.entity;

//import com.sun.xml.internal.ws.server.ServerRtException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EvaDis {
    String disId;
    String userId;
    String userName;
    String title;
    String dishScore;
    Date servTime;
    String content;
}
