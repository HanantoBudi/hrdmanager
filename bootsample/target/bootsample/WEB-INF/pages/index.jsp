<!DOCTYPE HTML>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Pragma" content="no-cache"> 
    <meta http-equiv="Cache-Control" content="no-cache"> 
    <meta http-equiv="Expires" content="Sat, 01 Dec 2001 00:00:00 GMT">
    
    <title>HRD Manager | Home</title>
    
    <link href="static/css/bootstrap.min.css" rel="stylesheet">
    <link href="static/css/style.css" rel="stylesheet">
    
    <link href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.1/themes/base/jquery-ui.css" rel="stylesheet" />
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.1/jquery-ui.min.js"></script>
    
    <script type="text/javascript" src="static/js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="static/js/jquery-ui-1.11.1.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/jquery-ui-min.css" />
	
    <!--[if lt IE 9]>
		<script src="static/js/html5shiv.min.js"></script>
		<script src="static/js/respond.min.js"></script>
	<![endif]-->
</head>
<body>

	<div role="navigation">
		<div class="navbar navbar-inverse">
			<a href="/" class="navbar-brand">Home</a>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li><a href="new-karyawans">New Karyawan</a></li>
					<li><a href="all-karyawans">Karyawan</a></li>
					<li><a href="new-cutis">New Cuti</a></li>
					<li><a href="all-cutis">Cuti</a></li>
					<li><a href="all-karyawans-tgl-gabung">Soal Nomor 2</a></li>
					<li><a href="all-karyawans-ambil-cuti">Soal Nomor 3</a></li>
					<li><a href="all-karyawans-ambil-cuti-lebih-dari-satu">Soal Nomor 4</a></li>
					<li><a href="all-karyawans-tidak-ambil-cuti">Soal Nomor 5</a></li>
				</ul>
			</div>
		</div>
	</div>
	<c:choose>
		<c:when test="${mode == 'MODE_HOME'}">
			<div class="container" id="homeDiv">
				<div class="jumbotron text-center">
					<h1>Welcome to HRD Manager</h1>
				</div>
			</div>
		</c:when>
		<c:when test="${mode == 'MODE_KARYAWAN'}">
			<div class="container text-center" id="taskDiv">
				<h1>KARYAWAN</h1>
				<hr>
				<div class="table-responsive">
					<table class="table table-striped table-bordered text-left">
						<thead>
							<tr>
								<th>No</th>
								<th>Nomor Induk</th>
								<th>Nama</th>
								<th>Alamat</th>
								<th>Tanggal Lahir</th>
								<th>Tanggal Bergabung</th>
								<th>Update</th>
								<th>Delete</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="karyawan" items="${karyawans}">
								<tr>
									<td>${karyawan.id}</td>
									<td>${karyawan.nomorInduk}</td>
									<td>${karyawan.nama}</td>
									<td>${karyawan.alamat}</td>
									<td><fmt:formatDate pattern="dd-MM-yyyy" value="${karyawan.tanggalLahir}"/></td>
									<td><fmt:formatDate pattern="dd-MM-yyyy" value="${karyawan.tanggalGabung}"/></td>
									<td><a href="update-karyawans?id=${karyawan.id}"><span class="glyphicon glyphicon-pencil"></span></a></td>
									<td><a href="delete-karyawans?id=${karyawan.id}"><span class="glyphicon glyphicon-trash"></span></a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</c:when>
		<c:when test="${mode == 'MODE_NEW_KARYAWAN' || mode == 'MODE_UPDATE_KARYAWAN' }">
			<div class="container text-center">
				<h1>Manage Karyawan</h1>
				<form class="form-horizontal" method="POST" action="save-karyawans">
					<input type="hidden" name="id" value="${karyawan.id}" />
					<div class="form-group">
						<label class="control-label col-md-3">Nomor Induk</label>
						<div class="col-md-7">
							<input type="text" required class="form-control" name="nomorInduk" value="${karyawan.nomorInduk}" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">Nama</label>
						<div class="col-md-7">
							<input type="text" required class="form-control" name="nama" value="${karyawan.nama}" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">Alamat</label>
						<div class="col-md-7">
							<input type="text" required class="form-control" name="alamat" value="${karyawan.alamat}" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">Tanggal Lahir</label>
						<div class="col-md-7">
							<%-- <input type="date" required id="datepicker" class="form-control" name="tanggalLahir" value="${karyawan.tanggalLahir}" /> --%>
							<div class="input-group">
								<input type="text" placeholder="mm-dd-yyyy" class="form-control" name="tanggalLahir" value="${karyawan.tanggalLahirText}" />
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-calendar"></span>
								</span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">Tanggal Bergabung</label>
						<div class="col-md-7">
							<%-- <input type="date" required class="form-control" name="tanggalGabung" value="${karyawan.tanggalGabung}" /> --%>
							<div class="input-group">
								<input type="text" placeholder="mm/dd/yyyy" class="form-control" name="tanggalGabung" value="${karyawan.tanggalGabungText}" />
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-calendar"></span>
								</span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<input type="submit" class="btn btn-primary" value="Save" />
					</div>
				</form>
			</div>
		</c:when>
		<c:when test="${mode == 'MODE_CUTI'}">
			<div class="container text-center" id="taskDiv">
				<h1>CUTI</h1>
				<hr>
				<div class="table-responsive">
					<table class="table table-striped table-bordered text-left">
						<thead>
							<tr>
								<th>Nomor Induk</th>
								<th>Nama</th>
								<th>Tanggal Cuti</th>
								<th>Lama Cuti</th>
								<th>Keterangan</th>
								<th>Update</th>
								<th>Delete</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="cuti" items="${cutis}">
								<tr>
									<td>${cuti.nomorInduk}</td>
									<td>${cuti.nama}</td>
									<td><fmt:formatDate pattern="dd-MM-yyyy" value="${cuti.tanggalCuti}"/></td>
									<td>${cuti.lamaCuti}</td>
									<td>${cuti.keterangan}</td>
									<td><a href="update-cutis?id=${cuti.id}"><span class="glyphicon glyphicon-pencil"></span></a></td>
									<td><a href="delete-cutis?id=${cuti.id}"><span class="glyphicon glyphicon-trash"></span></a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</c:when>
		<c:when test="${mode == 'MODE_NEW_CUTI' || mode == 'MODE_UPDATE_CUTI' }">
			<div class="container text-center">
				<h1>Manage Cuti</h1>
				<form class="form-horizontal" method="POST" action="save-cutis">
					<input type="hidden" name="id" value="${cuti.id}" />
					<div class="form-group">
						<label class="control-label col-md-3">Nomor Induk</label>
						<div class="col-md-7">
							<input type="text" required class="form-control" name="nomorInduk" value="${cuti.nomorInduk}" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">Tanggal Cuti</label>
						<div class="col-md-7">
							<div class="input-group" id="datetimepicker">
								<input type="text" placeholder="mm/dd/yyyy" class="form-control" name="tanggalCuti" value="${cuti.tanggalCuti}" />
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-calendar"></span>
								</span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">Lama Cuti</label>
						<div class="col-md-7">
							<input type="text" required class="form-control" name="lamaCuti" value="${cuti.lamaCuti}" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">Keterangan</label>
						<div class="col-md-7">
							<input type="text" required class="form-control" name="keterangan" value="${cuti.keterangan}" />
						</div>
					</div>
					<div class="form-group">
						<input type="submit" class="btn btn-primary" value="Save" />
					</div>
				</form>
			</div>
		</c:when>
		<c:when test="${mode == 'MODE_KARYAWAN_TGL_GABUNG'}">
			<div class="container text-center" id="taskDiv">
				<h1>3 KARYAWAN YANG PERTAMA KALI BERGABUNG</h1>
				<hr>
				<div class="table-responsive">
					<table class="table table-striped table-bordered text-left">
						<thead>
							<tr>
								<th>No</th>
								<th>Nomor Induk</th>
								<th>Nama</th>
								<th>Alamat</th>
								<th>Tanggal Lahir</th>
								<th>Tanggal Bergabung</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="k" items="${karyawans}">
								<tr>
									<td>${k.id}</td>
									<td>${k.nomorInduk}</td>
									<td>${k.nama}</td>
									<td>${k.alamat}</td>
									<td><fmt:formatDate pattern="dd-MM-yyyy" value="${k.tanggalLahir}"/></td>
									<td><fmt:formatDate pattern="dd-MM-yyyy" value="${k.tanggalGabung}"/></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</c:when>
		<c:when test="${mode == 'MODE_KARYAWAN_AMBIL_CUTI'}">
			<div class="container text-center" id="taskDiv">
				<h1>KARYAWAN YANG TELAH MENGAMBIL CUTI</h1>
				<hr>
				<div class="table-responsive">
					<table class="table table-striped table-bordered text-left">
						<thead>
							<tr>
								<th>Nomor Induk</th>
								<th>Nama</th>
								<th>Tanggal Cuti</th>
								<th>Keterangan</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="k" items="${karyawans}">
								<tr>
									<td>${k.nomorInduk}</td>
									<td>${k.nama}</td>
									<td><fmt:formatDate pattern="dd-MM-yyyy" value="${k.tanggalCuti}"/></td>
									<td>${k.keterangan}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</c:when>
		<c:when test="${mode == 'MODE_KARYAWAN_AMBIL_CUTI_LEBIH_DARI_SATU'}">
			<div class="container text-center" id="taskDiv">
				<h1>KARYAWAN YANG TELAH MENGAMBIL CUTI LEBIH DARI 1 KALI</h1>
				<hr>
				<div class="table-responsive">
					<table class="table table-striped table-bordered text-left">
						<thead>
							<tr>
								<th>Nomor Induk</th>
								<th>Nama</th>
								<th>Tanggal Cuti</th>
								<th>Keterangan</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="k" items="${karyawans}">
								<tr>
									<td>${k.nomorInduk}</td>
									<td>${k.nama}</td>
									<td><fmt:formatDate pattern="dd-MM-yyyy" value="${k.tanggalCuti}"/></td>
									<td>${k.keterangan}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</c:when>
		<c:when test="${mode == 'MODE_KARYAWAN_TIDAK_AMBIL_CUTI'}">
			<div class="container text-center" id="taskDiv">
				<h1>KARYAWAN YANG SISA CUTI ADALAH 12</h1>
				<hr>
				<div class="table-responsive">
					<table class="table table-striped table-bordered text-left">
						<thead>
							<tr>
								<th>Nomor Induk</th>
								<th>Nama</th>
								<th>Sisa Cuti</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="k" items="${karyawans}">
								<tr>
									<td>${k.nomorInduk}</td>
									<td>${k.nama}</td>
									<td>12</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</c:when>
	</c:choose>
	
    <script src="static/js/jquery-1.11.1.min.js"></script>    
    <script src="static/js/bootstrap.min.js"></script>
</body>
</html>