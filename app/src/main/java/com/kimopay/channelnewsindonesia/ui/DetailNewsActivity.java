package com.kimopay.channelnewsindonesia.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.kimopay.channelnewsindonesia.R;
import com.kimopay.channelnewsindonesia.adapter.CategoryAdapter;
import com.kimopay.channelnewsindonesia.adapter.KontenAdapter;
import com.kimopay.channelnewsindonesia.adapter.NewsMoreAdapter;
import com.kimopay.channelnewsindonesia.model.Category;
import com.kimopay.channelnewsindonesia.model.News;

import java.util.ArrayList;

public class DetailNewsActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private TextView tv_description;
    String news_desc = "<p><strong>Makassar, Channel N--</strong> Tagar #PercumaLaporPolisi menggema di Twitter sebagai bentuk reaksi atas penghentian penyelidikan &nbsp;kasus pemerkosaan tiga anak oleh ayah kandung di Kecamatan Malili, Kabupaten Luwu Timur, Sulawesi Selatan.<br />\n" +
            "Penyelidikan sudah dilakukan sejak Oktober 2019 dan telah dihentikan Polres Luwu Timur karena dianggap tidak cukup bukti. Pelaku yang berinisial SA dilaporkan ke polisi oleh mantan istrinya, RA, setelah diduga memerkosa tiga anak kandungnya yang kala itu masih berusia di bawah 10 tahun.<br />\n" +
            "<br />\n" +
            "Meskipun sudah melakukan penyelidikan, penanganan kasus ini diduga penuh manipulasi dan konflik kepentingan mengingat terduga pelaku merupakan seorang Aparatur Sipil Negara (ASN).<br />\n" +
            "<br />\n" +
            "Kini, setelah desakan datang dari sejumlah pihak, kasus tersebut dilaporkan telah dibuka kembali.&nbsp; Kapolres Luwu Timur AKBP Silvester MM Simamora menemui ibu tiga anak yang menjadi korban dugaan pemerkosaan oleh ayahnya. Polisi berjanji akan melanjutkan kasus tersebut hingga tuntas.<br />\n" +
            "<br />\n" +
            "Ibu korban, RA, mengatakan rombongan Kapolres Luwu Timur datang ke kediamannya pada Jumat (8/10) sore. Mereka membicarakan kasus yang sempat dilaporkan sebelum penyelidikannya dihentikan pada 2019.<br />\n" +
            "<br />\n" +
            "&quot;Barusan rombongan pak kapolres ke rumah ketemu saya langsung. Iya, mau dilanjut ini kasus. Semua yang jadi masalah kemarin kenapa kasus ini ditutup, akan ditindaklanjuti sama kapolres baru,&quot; tutur RA kepada CNNIndonesia.com.<br />\n" +
            "<br />\n" +
            "Meski kasus kini akhirnya sudah dibuka lagi, tapi apa yang mesti dilakukan jika laporan pengaduan diabaikan oleh polisi?Yang Harus Dilakukan Jika Polisi Abaikan Laporan PengaduanTagar #PercumaLaporPolisi menggema di Twitter sebagai bentuk reaksi atas penghentian penyelidikan &nbsp;kasus pemerkosaan tiga anak oleh ayah kandung di Kecamatan Malili, Kabupaten Luwu Timur, Sulawesi Selatan.<br />\n" +
            "Penyelidikan sudah dilakukan sejak Oktober 2019 dan telah dihentikan Polres Luwu Timur karena dianggap tidak cukup bukti. Pelaku yang berinisial SA dilaporkan ke polisi oleh mantan istrinya, RA, setelah diduga memerkosa tiga anak kandungnya yang kala itu masih berusia di bawah 10 tahun.<br />\n" +
            "<br />\n" +
            "Meskipun sudah melakukan penyelidikan, penanganan kasus ini diduga penuh manipulasi dan konflik kepentingan mengingat terduga pelaku merupakan seorang Aparatur Sipil Negara (ASN).Pada dasarnya anggota Polri dilarang untuk menolak atau mengabaikan permintaan pertolongan, bantuan, atau laporan dan pengaduan dari masyarakat yang menjadi lingkup tugas, fungsi, dan kewenangannya.<br />\n" +
            "<br />\n" +
            "Hal itu sebagaimana diatur dalam Pasal 15 Peraturan Kapolri Nomor 14 Tahun 2011 tentang Kode Etik Profesi Polisi (KEPP).<br />\n" +
            "<br />\n" +
            "Anggota Polri yang melanggar peraturan etika dapat dikenakan sanksi seperti meminta maaf; mengikuti pembinaan mental, kejiwaan, keagamaan, dan pengetahuan keagamaan; dipindahtugaskan ke jabatan dan fungsi berbeda yang bersifat demosi sekurang-kurangnya selama satu tahun; hingga pemecatan.<br />\n" +
            "<br />\n" +
            "Akun twitter konsultasi hukum, @justika_id, menjelaskan bahwa siapa pun bisa membuat aduan jika menemukan pelanggaran kode etik anggota kepolisian. Pelapor bisa membuat laporan ke Divisi Propam baik secara langsung ataupun lewat email.<br />\n" +
            "<br />\n" +
            "&quot;Dokumen yang harus disiapkan pada saat pengaduan adalah: identitas pelapor, kronologis peristiwa yang ingin diadukan,&quot; cuit @justika_id dikutip Jumat (8/10).</p>\n" +
            "\n" +
            "<ol>\n" +
            "\t<li>sasasa</li>\n" +
            "\t<li>asasacasc</li>\n" +
            "\t<li>sadsadas</li>\n" +
            "\t<li>dasdsa</li>\n" +
            "</ol>\n" +
            "\n" +
            "<p>Pengacara publik dari Lembaga Bantuan Hukum (LBH) Jakarta, Charlie Albajili, menambahkan pelapor bisa mengontrol kinerja polisi dalam menangani laporan dengan meminta Surat Pemberitahuan Perkembangan Hasil Penyidikan (SP2HP). Menerima SP2HP merupakan hak setiap pelapor.<br />\n" +
            "<br />\n" +
            "Apabila laporan pengaduan diabaikan oleh pihak kepolisian, terang dia, pelapor dapat membuat aduan.<br />\n" +
            "<br />\n" +
            "&quot;Kalau ada indikasi penundaan berlarut (undue delay), misalnya tidak ada SP2HP, tidak ada perkembangan dan upaya yang dilakukan polisi, bisa laporkan ke lembaga pengawas seperti&nbsp;Kompolnas dan Ombudsman RI. Di sini ada potensi juga penyidik melanggar pedoman perilakunya sehingga bisa diadukan ke Divisi Propram,&quot; kata Charlie.<br />\n" +
            "<br />\n" +
            "Ia menambahkan pelapor mempunyai hak untuk menggugat praperadilan ke pengadilan negeri setempat untuk menguji apabila terjadi penghentian penyidikan.<br />\n" +
            "<br />\n" +
            "&quot;Yang pasti tanggung jawab dan tugasnya polisi untuk mencari alat bukti, apalagi dalam kasus yang alat buktinya hanya polisi yang punya otoritas dapatkan, seperti visum et repertum, visum psikiatrikum, pengakuan saksi, atau bahkan bukti petunjuk seperti CCTV, dan lain-lain,&quot; tutur dia.<br />\n" +
            "<br />\n" +
            "&quot;Enggak bisa dibebankan ke korban meskipun tentu demi kepentingannya korban atau pelapor juga perlu sediakan sebisanya,&quot; tambah Charlie.</p>";

    private RecyclerView rv_kategori;
    private RecyclerView rv_news_more;
    private SwipeRefreshLayout swipe_continer;

    private CategoryAdapter categoryAdapter;
    private ArrayList<Category> categories;

    private NewsMoreAdapter newsMoreAdapter;
    private ArrayList<News> newsArrayList;

    private CollapsingToolbarLayout collaps_toolbar;
    private AppBarLayout appbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_news);

        Toolbar toolbar = findViewById(R.id.toolbar);
//        toolbar.setTitle("Detail Berita");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_chevron_left_24);

        collaps_toolbar = findViewById(R.id.collaps_toolbar);
        appbar = findViewById(R.id.appbar);
        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collaps_toolbar.setTitle("Detail Berita");
                    isShow = true;
                } else if(isShow) {
                    collaps_toolbar.setTitle(" ");//careful there should a space between double quote otherwise it wont work
                    isShow = false;
                }

            }
        });

        tv_description = findViewById(R.id.tv_description);
        rv_kategori = findViewById(R.id.rv_kategori);
        rv_news_more = findViewById(R.id.rv_news_more);

        swipe_continer = findViewById(R.id.swipe_continer);
        swipe_continer.setOnRefreshListener(this);
        swipe_continer.setColorSchemeResources(R.color.color_primary_cnn,
                android.R.color.holo_blue_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_green_dark);
        swipe_continer.post(new Runnable() {
            @Override
            public void run() {
                loadData();
            }
        });

    }

    private void loadData() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tv_description.setText(Html.fromHtml(news_desc, Html.FROM_HTML_MODE_COMPACT));
        } else {
            tv_description.setText(Html.fromHtml(news_desc));
        }

        // category
        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rv_kategori.setLayoutManager(horizontalLayoutManagaer);
        categoryAdapter = new CategoryAdapter(this, categories);
        rv_kategori.setAdapter(categoryAdapter);

        // news more
        LinearLayoutManager horizontalLayoutManagaer2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rv_news_more.setLayoutManager(horizontalLayoutManagaer2);
        newsMoreAdapter = new NewsMoreAdapter(this, newsArrayList);
        rv_news_more.setAdapter(newsMoreAdapter);



        swipe_continer.setRefreshing(false);

    }

    @Override
    public void onRefresh() {
        loadData();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}