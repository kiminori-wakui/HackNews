package com.example.hacknews.net

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize


data class GoodsDetailRsp(
    val itemInfoXML: ItemInfoXML
)


data class ItemInfoXML(
    val cVo: Any?,
    val item: GoodsDetailItem?,
    val rcd: Int
)


@Parcelize
@Keep
data class GoodsDetailItem(
    val itCd: String,
    val dBrId: String,
    val dBrNm: String,
    val bBrLg: String,
    val eBrId: String,
    val eBrK: String,
    val eBrNm: String,

    val itNm: String,
    val bph: Int,
    val bpl: Int,
    val sph: Int,
    val spl: Int,
    val pth: Int,
    val ptl: Int,
    val desc: String,
    val dispTagList: List<DispTag>,

    val dic: String,        //お問い合わせ商品番号
    val pcNm: String,
    val cNm: String,
    val mtr: String,
    val msTable: MsTable,

    val mainImgList: List<MainImg>,
    val coImgList: List<CoImg>,
    val subImgList: List<SubImg>,

    val skuList: List<Sku>,


    // val aFlg: Boolean,
    val allReFlg: Boolean,
    val reFlg: Boolean,

    // val bBrNm: String,
    // val bFlg: Boolean,

    val cFlg: Boolean,
    val nFlg: Boolean,
    val sFlg: Boolean,

    val cId: String,
    val pcId: String,

    val dch: Int,
    val dcl: Int,

    val saMeDiv: String,

    val favoriteCnt: Int,
    val cty: String?,
    // val categoryTagList: List<CategoryTag>,
    // val cmpItCd: String,
    //
    // val copFlg: Boolean,
    // val cpf: Boolean,
    // val crfs: Boolean,
    //

    // val dBrK: String,
    // val dBrLg: String,

    // val dFlg: Boolean,

    // val dcspcah: Any,
    // val dcspcal: Any,
    //
    // val dic: String,
    //

    // val exCoList: List<ExCo>,
    // val exSiList: List<ExSi>,

    // val fc1: Any,
    // val fc2: Any,
    // val fc3: Any,
    // val fc4: Any,
    // val fc5: Any,
    // val irf: Boolean,
    // val irik: String,

    // val itNm: String,
    // val ke: String,
    // val maCd: String,

    // val meList: Any,


    // val multipleTaxFlg: Boolean,

    // val nmNt: String,



    //
    // val raFlg: Boolean,
    // val reFlg: Boolean,
    // val reducedTaxAllFlg: Boolean,
    // val reducedTaxFlg: Boolean,
    // val remark: String,
    // val rmk1: String,
    // val rmk2: String,
    // val rmk3: String,
    val rpf: Boolean,
    // val rpwh: Int,
    // val rpwl: Int,
    //
    val sList: List<S>?,
    // val saDiv: String,
    // val saItCd: String,
    //
    // val saStDt: String,
    // val searchTagList: List<SearchTag>,
    // val secFlg: Boolean,

    // val soFlg: Boolean,

    // val spwh: Int,
    // val spwl: Int,

    // val vPtList: List<VPt>,
    // val vTypeList: List<VType>,
    // val wic: String,
    // val yrspf: Boolean,
    // val yssn: String,
    val caList: List<ca>,
) : Parcelable{}

@Parcelize
@Keep
data class S(
    val sInfo: SInfo,
    var isSelected: Boolean = false
) : Parcelable{}

@Parcelize
@Keep
data class SInfo(
    val id: String,
    val lineNo: Int,
    val nm: String
) : Parcelable{}



@Parcelize
@Keep
data class ca(
    val ca: CaX
) : Parcelable{}

@Parcelize
@Keep
data class CaX(
    val grpId: String
) : Parcelable{}


@Parcelize
@Keep
data class DispTag(
    val dispTag: DispTagX
) : Parcelable{}

@Parcelize
@Keep
data class DispTagX(
    val id: String,
    val nm: String
) : Parcelable{}

@Parcelize
@Keep
data class MsTable(
    val msInfoList: List<MsInfo>,
    val remark: String?
) : Parcelable{}

@Parcelize
@Keep
data class MsInfo(
    val id: String,
    val lineNo: String,
    val msList: List<Ms>,
    val nm: String
) : Parcelable{}

@Parcelize
@Keep
data class Ms(
    val dtl: String,
    val label: String
) : Parcelable{}


@Parcelize
@Keep
data class Sku(
    val skuInfo: SkuInfo
) : Parcelable{}



@Parcelize
@Keep
data class SkuInfo(
    val aFlg: Boolean,
    val bp: Int,
    val bpwt: Int,
    val caLm: Int,
    val dCoId: String,
    val dCoNm: String,
    val dc: Int,
    // val dcspca: Any,
    val eCoId: String,
    val eCoNm: String,
    val eSiId: String,
    val eSiNm: String,
    val limitedStock: Int,
    val limitedStockFlg: Boolean,
    val nFlg: Boolean,
    val poFlg: Boolean,
    val pt: Int,
    val pubEdDt: String,
    val pubFlg: Boolean,
    val pubStDt: String,
    // val raDt: Any,
    val raFlg: Boolean,
    val reFlg: Boolean,
    val reFuFlg: Boolean,
    val reducedTaxFlg: Boolean,
    val sFlg: Boolean,
    val sId: String,
    val sNm: String,
    val saDiv: String,
    val saEdDt: String,
    val saItId: String,
    val saSkuCd: String,
    val saStDt: String,
    val saStatus: String,
    val secFlg: Boolean,
    val shDt: String,
    val shDtBe: String,
    val shDtBme: String,
    val shDtMax: Int,
    val shDtMin: Int,
    val shippingDtReqFlg: Boolean,
    val soFlg: Boolean,
    val sp: Int,
    val spwt: Int,
    val taxDiv: String,
    val rpf: Boolean
) : Parcelable{}


@Parcelize
@Keep
data class CoImg(
    val imgInfo: ImgInfo,
    var isSelected: Boolean = false
) : Parcelable{}


@Parcelize
@Keep
data class MainImg(
    val imgInfo: ImgInfo
) : Parcelable{}

@Parcelize
@Keep
data class SubImg(
    val imgInfo: ImgInfo
) : Parcelable{}


@Parcelize
@Keep
data class ImgInfoList(
//    var imgs: MutableList<String> = mutableListOf()
     var imgs: MutableList<ImgInfo> = mutableListOf()
) : Parcelable {
}



@Parcelize
@Keep
data class ImgInfo(
    var comment: String?="",
    var dcoNm: String?="",
    var id: String="",
    var ord: Int=0,
    var path: String="",
    var colorId: String="ZZZZ"
) : Parcelable {
}























