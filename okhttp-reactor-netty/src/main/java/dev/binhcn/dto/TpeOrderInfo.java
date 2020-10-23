package dev.binhcn.dto;

import java.util.List;
import lombok.Data;

@Data
public class TpeOrderInfo {
  private int appid;
  private String apptransid;
  private String appuser;
  private long apptime;
  private String embeddata;
  private String item;
  private long amount;
  private String description;
  private String campaigncode;
  private String mac;
  private String tpebankcode;
  private int zpgwnextstep;
  private String zpgwnextstepdata;
  private long discountamount;
  private String subappuser;
  private String subappid;
  private long zptransid;
  private boolean isProcessing;
  private String suggestmessage;
  private List<Integer> suggestaction;
  private String sub_error_code;
  private int returncode;
  private String returnmessage;
}
