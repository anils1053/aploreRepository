package co.nz.aplore.business.converter;

import java.util.ArrayList;
import java.util.List;

import co.nz.aplore.business.dto.ClinicalEventEndPointDto;
import co.nz.aplore.business.dto.PathParameterDto;
import co.nz.aplore.business.dto.PayloadParameterDto;
import co.nz.aplore.business.dto.ProductDetailsDto;
import co.nz.aplore.business.dto.ProductDto;
import co.nz.aplore.business.dto.ProductSummaryDto;
import co.nz.aplore.business.dto.QueryParameterDto;
import co.nz.aplore.business.dto.RestEndPointDto;
import co.nz.aplore.business.dto.SoapEndPointDto;
import co.nz.aplore.persistence.mongo.entity.ClinicalEventEndPointEntity;
import co.nz.aplore.persistence.mongo.entity.PathParameterEntity;
import co.nz.aplore.persistence.mongo.entity.PayloadParameterEntity;
import co.nz.aplore.persistence.mongo.entity.ProductDetailsEntity;
import co.nz.aplore.persistence.mongo.entity.ProductEntity;
import co.nz.aplore.persistence.mongo.entity.ProductSummaryEntity;
import co.nz.aplore.persistence.mongo.entity.QueryParameterEntity;
import co.nz.aplore.persistence.mongo.entity.RestEndPointEntity;
import co.nz.aplore.persistence.mongo.entity.SoapEndPointEntity;

public class ProductConverter {

	public static ProductDto copyProudctDto(final String productId, final ProductDetailsDto details, final List<RestEndPointDto> rest,
			final List<SoapEndPointDto> soap, final List<ClinicalEventEndPointDto> clinicalEvent) {
		final ProductDto dest = new ProductDto();
		dest.setProductId(productId);
		dest.setProductDetails(details);
		dest.setRestEndPoints(rest);
		dest.setSoapEndPoints(soap);
		dest.setClinicalEventEndPoints(clinicalEvent);
		return dest;
	}

	protected static void createOrUpdateIfExists(final ProductEntity entityInDb, final RestEndPointEntity newRestEndPointEntity) {
		final List<RestEndPointEntity> restEndPoints = entityInDb.getRestEndPoints();
		if (restEndPoints == null) {
			entityInDb.setRestEndPoints(new ArrayList<RestEndPointEntity>());
		}
		final List<RestEndPointEntity> restEndPointsFromDb = entityInDb.getRestEndPoints();
		if (restEndPointsFromDb.contains(newRestEndPointEntity)) {
			for (final RestEndPointEntity endPointToUpdate : restEndPointsFromDb) {
				if (endPointToUpdate.equals(newRestEndPointEntity)) {
					copyRestEntryPointValues(endPointToUpdate, newRestEndPointEntity);
					break;
				}
			}
		} else {
			restEndPointsFromDb.add(newRestEndPointEntity);
		}
	}

	public static ProductEntity convertToProductEntity(final ProductDto dto, final ProductEntity entity) {
		if (dto == null || entity == null) {
			return null;
		}
		entity.setProductId(dto.getProductId());

		// details
		final ProductDetailsEntity detailEntity = new ProductDetailsEntity();
		final ProductDetailsDto productDetailsDto = dto.getProductDetails();
		detailEntity.setJavadocBaseUrl(productDetailsDto.getJavadocBaseUrl());
		detailEntity.setMajorVersion(productDetailsDto.getMajorVersion());
		detailEntity.setMinorVersion(productDetailsDto.getMinorVersion());
		detailEntity.setProductName(productDetailsDto.getProductName());
		detailEntity.setServicePackVersion(productDetailsDto
				.getServicePackVersion());
		detailEntity.setMilestone(productDetailsDto.getMilestone());
		entity.setProductDetails(detailEntity);

		// RestEndPointEntity list
		for (final RestEndPointDto rest : dto.getRestEndPoints()) {
			final RestEndPointEntity restEntity = new RestEndPointEntity();
			restEntity.setJavaClass(rest.getJavaClass());
			restEntity.setJavaDocUrl(rest.getJavaDocUrl());
			restEntity.setJavaMethodName(rest.getJavaMethodName());
			restEntity.setMethod(rest.getMethod());
			restEntity.setUri(rest.getUri());

			// Loop to build Path params
			final List<PathParameterEntity> pathParamEntities = new ArrayList<>();
			for (final PathParameterDto param : rest.getPathParameters()) {
				final PathParameterEntity pathParamEntity = new PathParameterEntity();
				pathParamEntity.setJavaType(param.getJavaType());
				pathParamEntity.setName(param.getName());
				pathParamEntity.setParameterType(param.getParameterType());
				pathParamEntities.add(pathParamEntity);
			}
			restEntity.setPathParameters(pathParamEntities);

			// payloads
			final List<PayloadParameterEntity> payloadEntities = new ArrayList<>();
			for (final PayloadParameterDto payload : rest
					.getPayloadParameters()) {
				final PayloadParameterEntity payloadEntity = new PayloadParameterEntity();
				payloadEntity.setJavaType(payload.getJavaType());
				payloadEntity.setParameterType(payload.getParameterType());
				payloadEntities.add(payloadEntity);
			}
			restEntity.setPayloadParameters(payloadEntities);

			// query params
			final List<QueryParameterEntity> queryEntities = new ArrayList<>();
			for (final QueryParameterDto query : rest.getQueryParameters()) {
				final QueryParameterEntity queryEntity = new QueryParameterEntity();
				queryEntity.setJavaType(query.getJavaType());
				queryEntity.setName(query.getName());
				queryEntity.setParameterType(query.getParameterType());
				queryEntities.add(queryEntity);
			}
			restEntity.setQueryParameters(queryEntities);
			createOrUpdateIfExists(entity, restEntity);
		}

		// SoapEndPointEntity list
		final List<SoapEndPointEntity> soapEntities = new ArrayList<>();
		final List<SoapEndPointDto> soapEndPoints = dto.getSoapEndPoints();
		if (soapEndPoints != null) {
			for (final SoapEndPointDto soap : soapEndPoints) {
				final SoapEndPointEntity soapEntity = new SoapEndPointEntity();
				soapEntity.setJavaClass(soap.getJavaClass());
				soapEntity.setJavaDocUrl(soap.getJavaDocUrl());
				soapEntity.setJavaMethodName(soap.getJavaMethodName());
				soapEntity.setMethod(soap.getMethod());
				soapEntities.add(soapEntity);
			}
		}
		entity.setSoapEndPoints(soapEntities);

		// ClinicalEventEndPointEntity list
		// SoapEndPointEntity list
		final List<ClinicalEventEndPointEntity> clinicalEventEntities = new ArrayList<>();
		final List<ClinicalEventEndPointDto> clinicalEventEndPoints = dto.getClinicalEventEndPoints();
		if (clinicalEventEndPoints != null) {
			for (final ClinicalEventEndPointDto clinicalEvent : clinicalEventEndPoints) {
				final ClinicalEventEndPointEntity clinicalEventEntity = new ClinicalEventEndPointEntity();
				clinicalEventEntity.setJavaClass(clinicalEvent.getJavaClass());
				clinicalEventEntity.setPackageName(clinicalEvent.getPackageName());
				clinicalEventEntity.setJavadocLink(clinicalEvent.getJavadocLink());
				clinicalEventEntities.add(clinicalEventEntity);
			}
		}
		entity.setClinicalEventEndPoints(clinicalEventEntities);

		return entity;
	}

	private static void copyRestEntryPointValues(final RestEndPointEntity restEntity, final RestEndPointEntity rest) {
		restEntity.setJavaClass(rest.getJavaClass());
		restEntity.setJavaDocUrl(rest.getJavaDocUrl());
		restEntity.setJavaMethodName(rest.getJavaMethodName());
		restEntity.setMethod(rest.getMethod());
		restEntity.setUri(rest.getUri());

		// Loop to build Path params
		final List<PathParameterEntity> pathParamEntities = new ArrayList<>();
		for (final PathParameterEntity param : rest.getPathParameters()) {
			final PathParameterEntity pathParamEntity = new PathParameterEntity();
			pathParamEntity.setJavaType(param.getJavaType());
			pathParamEntity.setName(param.getName());
			pathParamEntity.setParameterType(param.getParameterType());
			pathParamEntities.add(pathParamEntity);
		}
		restEntity.setPathParameters(pathParamEntities);

		// payloads
		final List<PayloadParameterEntity> payloadEntities = new ArrayList<>();
		for (final PayloadParameterEntity payload : rest.getPayloadParameters()) {
			final PayloadParameterEntity payloadEntity = new PayloadParameterEntity();
			payloadEntity.setJavaType(payload.getJavaType());
			payloadEntity.setParameterType(payload.getParameterType());
			payloadEntities.add(payloadEntity);
		}
		restEntity.setPayloadParameters(payloadEntities);

		// query params
		final List<QueryParameterEntity> queryEntities = new ArrayList<>();
		for (final QueryParameterEntity query : rest.getQueryParameters()) {
			final QueryParameterEntity queryEntity = new QueryParameterEntity();
			queryEntity.setJavaType(query.getJavaType());
			queryEntity.setName(query.getName());
			queryEntity.setParameterType(query.getParameterType());
			queryEntities.add(queryEntity);
		}
		restEntity.setQueryParameters(queryEntities);
	}

	public static ProductDetailsDto convertToProductDetailsDto(
			final ProductEntity productEntity) {
		if (productEntity == null) {
			return null;
		}
		final ProductDetailsDto detailDto = new ProductDetailsDto();
		final ProductDetailsEntity productDetailsEntity = productEntity
				.getProductDetails();
		detailDto.setJavadocBaseUrl(productDetailsEntity.getJavadocBaseUrl());
		detailDto.setMajorVersion(productDetailsEntity.getMajorVersion());
		detailDto.setMinorVersion(productDetailsEntity.getMinorVersion());
		detailDto.setProductName(productDetailsEntity.getProductName());
		detailDto.setServicePackVersion(productDetailsEntity
				.getServicePackVersion());
		detailDto.setMilestone(productDetailsEntity.getMilestone());
		detailDto.setProductId(productEntity.getProductId());
		detailDto.setEntityId(productEntity.getId());
		return detailDto;
	}

	public static ProductDto convertToProductDto(
			final ProductEntity productEntity) {
		if (productEntity == null) {
			return null;
		}
		final ProductDto productDto = new ProductDto();

		productDto.setProductId(productEntity.getProductId());

		// details
		final ProductDetailsDto detailDto = convertToProductDetailsDto(productEntity);
		productDto.setProductDetails(detailDto);

		// RestEndPointEntity list
		final List<RestEndPointDto> restDtos = new ArrayList<>();
		final List<RestEndPointEntity> restEndPoints = productEntity
				.getRestEndPoints();
		if (restEndPoints != null) {
			for (final RestEndPointEntity rest : restEndPoints) {
				final RestEndPointDto restDto = new RestEndPointDto();
				restDto.setJavaClass(rest.getJavaClass());
				restDto.setJavaDocUrl(rest.getJavaDocUrl());
				restDto.setJavaMethodName(rest.getJavaMethodName());
				restDto.setMethod(rest.getMethod());
				restDto.setUri(rest.getUri());

				// Loop to build Path params
				final List<PathParameterDto> pathParamDtos = new ArrayList<>();
				final List<PathParameterEntity> pathParameters = rest
						.getPathParameters();
				if (pathParameters != null) {
					for (final PathParameterEntity param : pathParameters) {
						final PathParameterDto pathParamDto = new PathParameterDto();
						pathParamDto.setJavaType(param.getJavaType());
						pathParamDto.setName(param.getName());
						pathParamDto.setParameterType(param.getParameterType());
						pathParamDtos.add(pathParamDto);
					}
				}
				restDto.setPathParameters(pathParamDtos);

				// payloads
				final List<PayloadParameterDto> payloadDtos = new ArrayList<>();
				final List<PayloadParameterEntity> payloadParameters = rest
						.getPayloadParameters();
				if (payloadParameters != null) {
					for (final PayloadParameterEntity payload : payloadParameters) {
						final PayloadParameterDto payloadDto = new PayloadParameterDto();
						payloadDto.setJavaType(payload.getJavaType());
						payloadDto.setParameterType(payload.getParameterType());
						payloadDtos.add(payloadDto);
					}
				}
				restDto.setPayloadParameters(payloadDtos);

				// query params
				final List<QueryParameterDto> queryDtos = new ArrayList<>();
				final List<QueryParameterEntity> queryParameters = rest
						.getQueryParameters();
				if (queryParameters != null) {
					for (final QueryParameterEntity query : queryParameters) {
						final QueryParameterDto queryDto = new QueryParameterDto();
						queryDto.setJavaType(query.getJavaType());
						queryDto.setName(query.getName());
						queryDto.setParameterType(query.getParameterType());
						queryDtos.add(queryDto);
					}
				}
				restDto.setQueryParameters(queryDtos);

				restDtos.add(restDto);
			}
		}
		productDto.setRestEndPoints(restDtos);

		// Set the soap endpoints
		final List<SoapEndPointDto> soapDtos = new ArrayList<>();
		final List<SoapEndPointEntity> soapEndPoints = productEntity
				.getSoapEndPoints();
		if (soapEndPoints != null) {
			for (final SoapEndPointEntity soap : soapEndPoints) {
				final SoapEndPointDto soapDto = new SoapEndPointDto();
				soapDto.setJavaClass(soap.getJavaClass());
				soapDto.setJavaDocUrl(soap.getJavaDocUrl());
				soapDto.setJavaMethodName(soap.getJavaMethodName());
				soapDto.setMethod(soap.getMethod());
				soapDtos.add(soapDto);
			}
		}
		productDto.setSoapEndPoints(soapDtos);

		// Set the ClinicalEvent endpoints
		final List<ClinicalEventEndPointDto> clinicalEventDtos = new ArrayList<>();
		final List<ClinicalEventEndPointEntity> clinicalEventEndPoints = productEntity
				.getClinicalEventEndPoints();
		if (clinicalEventEndPoints != null) {
			for (final ClinicalEventEndPointEntity clinicalEvent : clinicalEventEndPoints) {
				final ClinicalEventEndPointDto clinicalEventDto = new ClinicalEventEndPointDto();
				clinicalEventDto.setJavaClass(clinicalEvent.getJavaClass());
				clinicalEventDto.setPackageName(clinicalEvent.getPackageName());
				clinicalEventDto.setJavadocLink(clinicalEvent.getJavadocLink());
				clinicalEventDtos.add(clinicalEventDto);
			}
		}
		productDto.setClinicalEventEndPoints(clinicalEventDtos);

		return productDto;
	}

	public static ProductSummaryDto convertToProductSummaryDto(
			final ProductSummaryEntity entity) {
		if (entity == null) {
			return null;
		}
		final ProductSummaryDto dto = new ProductSummaryDto();
		dto.setName(entity.getName());
		dto.setSummary(entity.getSummary());
		return dto;
	}

	public static ProductSummaryEntity convertToProductSummaryEntity(
			final ProductSummaryDto summaryDto,
			final ProductSummaryEntity entity) {
		if (summaryDto == null || entity == null) {
			return null;
		}
		entity.setName(summaryDto.getName());
		entity.setSummary(summaryDto.getSummary());
		return entity;
	}
}
