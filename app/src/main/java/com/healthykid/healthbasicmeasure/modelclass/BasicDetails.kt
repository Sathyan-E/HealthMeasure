package com.healthykid.healthbasicmeasure.modelclass

//import com.google.firebase.database.IgnoreExtraProperties
import com.google.gson.annotations.SerializedName


 class BasicDetails {
  @SerializedName("Blood_Group")
  var sBlood: String? = ""
  @SerializedName("Class")
  var sClass: String? = ""
  @SerializedName("DoB")
  var sDob: String? = ""
  @SerializedName("Emergency_Contact_Name")
  var semergencyContactName: String? = ""
  @SerializedName("Emergency_Contact_PhoneNo")
  var semergencyContactNo: String? = ""
  @SerializedName("Emergency_Contact_Relation")
  var semergencyContactRelation: String? = ""
  @SerializedName("Father_Blood_Group")
  var sfatherbp: String? = ""
  @SerializedName("Father_Name")
  var sFatherName: String? = ""
  @SerializedName("Father_Phone_No")
  var sFatherphone: String? = ""
  //@SerializedName("HealthReportDates")
  //var shealthreports: List<String>? = null
  //@SerializedName("HealthScreeningDates")
  //var shealthscreendates: List<String>? = null
  @SerializedName("Home_Addr")
  var address:String?=""
  @SerializedName("Immunization_Photo_Taken")
  var imPhotoTkaen:String?=""
  @SerializedName("LastAudiometryTestTakenOn")
  var lastAuditTaken:String?=""
  @SerializedName("LastBasicHealthCheckDoneOn")
  var lastBasictaken:String?=""
  @SerializedName("LastColorblindnessTestTakeOn")
  var lastColorTaeknOn:String?=""
  @SerializedName("Mother_Blood_Group")
  var motherBp:String?=""
  @SerializedName("Mother_Name")
  var sMotherName: String? = ""
  @SerializedName("Mother_Phone_No")
  var sMotherphone: String? = ""
  @SerializedName("Name")
  var sName: String? = ""
  @SerializedName("RecordCreatedOn")
  var recordCreated:String?=""
  @SerializedName("Roll_No")
  var sRollNumber: String? = ""
  @SerializedName("SchoolID")
  var schoolId:String?=""
  //@SerializedName("SecondHealthScreeningDates")
  //var secondHealthDates:List<String>?=null
  @SerializedName("Section")
  var sSection: String? = ""
  @SerializedName("Sex")
  var sSex: String? = ""
  @SerializedName("Student_Photo_Taken")
  var isPhotoTaken: String? = ""
  @SerializedName("Student_behaviour_issues")
  var sbehaviourissue:String?=""
  @SerializedName("Student_known_issues")
  var sKnownAlrgy:String?=""
  @SerializedName("Student_known_illness")
  var sKnownIllness:String?=""
  @SerializedName("Student_medical_condition")
  var sMedicalCondition:String?=""
  @SerializedName("Student_medication_name")
  var sMedicationName:String?=""
  @SerializedName("Student_regular_medication")
  var sRegularMedication:String?=""
  
 }