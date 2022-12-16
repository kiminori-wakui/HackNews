package com.example.hacknews.net

import android.os.Parcelable
import androidx.annotation.Keep

import kotlinx.parcelize.Parcelize


@Parcelize
@Keep
data class GoodsListRsp(
    val itemSearchInfoXML: ItemSearchInfoXML
) : Parcelable{

}


@Parcelize
@Keep
data class ItemSearchInfoXML(
    val itemSearchInfo: ItemSearchInfo,
    val qy: String,
    val rcd: Int
) : Parcelable{

}


@Parcelize
@Keep
data class ItemSearchInfo(
    val endNo: Int,
    val facet: Facet,
    val itL: List<ItL>,
    val maxPgNo: Int,
    val pgNo: Int,
    val startNo: Int,
    val totalHit: Int
) : Parcelable{

}



@Parcelize
@Keep
data class ItL(
    val `it`: It
) : Parcelable{

}

@Parcelize
@Keep
data class It(
    //user add
    var skuId: String = "",
    val itNm: String = "",


    var rank: Int = 0,
    var isLike: Boolean = false,

    val cId: String = "",        //アイテムカテゴリID
    var cNm: String = "",           //アイテムカテゴリ名
    var img: String = "",        //商品画像Path
    val itCd: String = "",       //基本アイテムコード
    val dBrId: String = "",      //表示ブランドID
    var dBrNm: String = "",      //表示ブランド名
    val dBrLg: String = "",      //表示ブランド画像
    val itUrl: String = "",      //商品詳細URL
    var bph: Int = 0,           //標準価格高（税込）
    var bpl: Int = 0,
    var sph: Int = 0,           //販売価格高（税込）
    var spl: Int = 0,
    val dch: Int = 0,           //割引率高
    val dcl: Int = 0,
    val soFlg: Boolean = false,     //もうすぐフラグ
    val nFlg: Boolean = false,      //新着フラグ
    val sFlg: Boolean = false,      //SOLD OUTフラグ

    val caList: List<ca> = listOf(),

    val allReFlg: Boolean = false,      //予約フラグ（すべて予約）

    // val raFlg: Boolean,     //再入荷フラグ
    //
    // val saDt: String,       //販売開始日時 yyyymmddhh24miss
    //
    //

    // val bFlg: Boolean,          //取寄フラグ
    //
    //
    // val caFlg: Boolean,
    // val categoryItemTagList: Any,
    // val copFlg: Boolean,
    // val cpf: Boolean,
    // val crfs: Boolean,
    //
    // val dBrKn: String,
    //
    //
    // val dCoCnt: Int,
    // val dFlg: Boolean,
    // val dSiNmMax: String,
    // val dSiNmMin: String,

    // val dcspcah: Any,
    // val dcspcal: Any,
    // val dispTagList: List<DispTag>,
    val exCoList: List<ExCo>,
    // val exSiList: List<ExSi>,
    // val fc1: Any,
    // val fc2: Any,
    // val fc3: Any,
    // val fc4: Any,
    // val fc5: Any,
    //
    // val includeNoSalesItemFlag: Boolean,
    // val irf: Boolean,
    //

    //
    // val itemListDisplay: Boolean,
    // val itemReviewIntegrationKey: Any,
    // val maCd: String,
    // val meFlg: Boolean,
    // val meNm: String,
    //
    // val pth: Int,
    // val ptl: Int,
    //
    val reFlg: Boolean,
    // val revc: Int,
    // val revs: Double,
    val rpf: Boolean,
    // val rpid: String,
    // val rpwh: Int,
    // val rpwl: Int,
    //
    //
    // val salesDivList: List<String>,
    // val searchItemTagList: Any,
    // val secFlg: Boolean,
    // val soFlg: Boolean,
    //
    // val spwh: Int,
    // val spwl: Int,
    // val sr1: Any,
    // val sr2: Any,
    // val ssph: Int,
    // val sspl: Int,
    // val yrspf: Boolean,
    // val yssn: String
) : Parcelable{
}



@Parcelize
@Keep
data class Facet(
    val coFcList: List<CoFc>,
    val dBrFcList: List<DBrFc>,
    val exBrFcList: List<ExBrFc>,
    val itcaFcList: List<ItcaFc>,
    val itemTagFcList: List<ItemTagFc>,
//     val opDateFcList: List<OpDateFc>,
    val sizeFcList: List<SizeFc>,
//     val sizeGrFcList: List<Any>,
//     val stDateFcList: List<StDateFc>
) : Parcelable{
}






@Parcelize
@Keep
data class CoFc(
 val f: F
) : Parcelable{
}



@Parcelize
@Keep
data class DBrFc(
 val f: FX
) : Parcelable{
}



@Parcelize
@Keep
data class ExBrFc(
    val f: FXX
) : Parcelable{
}





@Parcelize
@Keep
data class ItcaFc(
    val f: FXXX
) : Parcelable{
}


 @Parcelize
 @Keep
 data class ItemTagFc(
     val f: FXXXXX
 ) : Parcelable{
 }


// @Parcelize
// @Keep
// data class OpDateFc(
//     val f: FXXXXXXX
// )
//
//
 @Parcelize
 @Keep
 data class SizeFc(
     val f: SizeItemBean
 ) : Parcelable{
}
//
//
// @Parcelize
// @Keep
// data class StDateFc(
//     val f: FXXXXXXXXX
// )
//
//
@Parcelize
@Keep
data class F(
 val hit: Int,
 val id: String,
 val nm: String,
 val ord: Int,
// val sig: Any
) : Parcelable{
}


 @Parcelize
 @Keep
 data class FX(
     val hit: Int,
     val id: String,
     val k: String,
     val nm: String,
     val ord: Int,
//     val repExBrId: Any,
//     val sig: Any
 ) : Parcelable{
 }



@Parcelize
@Keep
data class FXX(
    val hit: Int,
    val id: String,
    val k: String,
    val nm: String,
    val ord: Int,
    val urlName: String
) : Parcelable{
}



@Parcelize
@Keep
data class FXXX(
    val childL: List<ChildL>,
    val hit: Int,
    val id: String,
    val nm: String,
    val ord: Int,
    // val sig: Any
) : Parcelable{
}



@Parcelize
@Keep
data class ChildL(
    val f: FXXXX
) : Parcelable{
}

@Parcelize
@Keep
data class FXXXX(
    // val childL: List<Any>,
    val hit: Int,
    val id: String,
    val nm: String,
    val ord: Int,
    // val sig: Any
) : Parcelable{
}


 @Parcelize
 @Keep
 data class FXXXXX(
     val childL: List<ChildLX>,
     val hits: Int,
     val id: String,
     val nm: String,
//     val sig: Any
 ) : Parcelable{
 }


@Parcelize
@Keep
data class ChildLX(
 val f: FXXXXXX
) : Parcelable{
}


 @Parcelize
 @Keep
 data class FXXXXXX(
//     val childL: List<Any>,
     val hits: Int,
     val id: String,
     val nm: String,
//     val sig: Any
 ) : Parcelable{
 }


// @Parcelize
// @Keep
// data class FXXXXXXX(
//     val dt: String,
//     val hit: Int
// )
//
//
 @Parcelize
 @Keep
 data class SizeItemBean(
     val hit: Int = 0,
     val id: String = "",
     val nm: String = "",
     val ord: Int = 0,
//     val sig: Any

    var sel: Boolean = false
 ) : Parcelable{
}
//
// @Parcelize
// @Keep
// data class FXXXXXXXXX(
//     val dt: String,
//     val hit: Int
// )
//
//
//
//
// @Parcelize
// @Keep
// data class DispTag(
//     val dispTag: DispTagX
// )
//
//
@Parcelize
@Keep
data class ExCo(
    val exCo: ExCoX
) : Parcelable

//
//
// @Parcelize
// @Keep
// data class ExSi(
//     val exSi: ExSiX
// )
//
//
// @Parcelize
// @Keep
// data class DispTagX(
//     val id: String,
//     val nm: String
// )
//
//
@Parcelize
@Keep
data class ExCoX(
    val id: String,
    val nm: String
) : Parcelable
//
//
// @Parcelize
// @Keep
// data class ExSiX(
//     val id: String,
//     val nm: String
// )
