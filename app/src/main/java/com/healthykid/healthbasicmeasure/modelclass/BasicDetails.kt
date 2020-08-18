package com.healthykid.healthbasicmeasure.modelclass

//import com.google.firebase.database.IgnoreExtraProperties
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


 class BasicDetails() :Parcelable {
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

  constructor(parcel: Parcel) : this() {
   sBlood = parcel.readString()
   sClass = parcel.readString()
   sDob = parcel.readString()
   semergencyContactName = parcel.readString()
   semergencyContactNo = parcel.readString()
   semergencyContactRelation = parcel.readString()
   sfatherbp = parcel.readString()
   sFatherName = parcel.readString()
   sFatherphone = parcel.readString()
   address = parcel.readString()
   imPhotoTkaen = parcel.readString()
   lastAuditTaken = parcel.readString()
   lastBasictaken = parcel.readString()
   lastColorTaeknOn = parcel.readString()
   motherBp = parcel.readString()
   sMotherName = parcel.readString()
   sMotherphone = parcel.readString()
   sName = parcel.readString()
   recordCreated = parcel.readString()
   sRollNumber = parcel.readString()
   schoolId = parcel.readString()
   sSection = parcel.readString()
   sSex = parcel.readString()
   isPhotoTaken = parcel.readString()
   sbehaviourissue = parcel.readString()
   sKnownAlrgy = parcel.readString()
   sKnownIllness = parcel.readString()
   sMedicalCondition = parcel.readString()
   sMedicationName = parcel.readString()
   sRegularMedication = parcel.readString()
  }

  override fun writeToParcel(parcel: Parcel, flags: Int) {
   parcel.writeString(sBlood)
   parcel.writeString(sClass)
   parcel.writeString(sDob)
   parcel.writeString(semergencyContactName)
   parcel.writeString(semergencyContactNo)
   parcel.writeString(semergencyContactRelation)
   parcel.writeString(sfatherbp)
   parcel.writeString(sFatherName)
   parcel.writeString(sFatherphone)
   parcel.writeString(address)
   parcel.writeString(imPhotoTkaen)
   parcel.writeString(lastAuditTaken)
   parcel.writeString(lastBasictaken)
   parcel.writeString(lastColorTaeknOn)
   parcel.writeString(motherBp)
   parcel.writeString(sMotherName)
   parcel.writeString(sMotherphone)
   parcel.writeString(sName)
   parcel.writeString(recordCreated)
   parcel.writeString(sRollNumber)
   parcel.writeString(schoolId)
   parcel.writeString(sSection)
   parcel.writeString(sSex)
   parcel.writeString(isPhotoTaken)
   parcel.writeString(sbehaviourissue)
   parcel.writeString(sKnownAlrgy)
   parcel.writeString(sKnownIllness)
   parcel.writeString(sMedicalCondition)
   parcel.writeString(sMedicationName)
   parcel.writeString(sRegularMedication)
  }

  override fun describeContents(): Int {
   return 0
  }

  companion object CREATOR : Parcelable.Creator<BasicDetails> {
   override fun createFromParcel(parcel: Parcel): BasicDetails {
    return BasicDetails(parcel)
   }

   override fun newArray(size: Int): Array<BasicDetails?> {
    return arrayOfNulls(size)
   }
  }

 }